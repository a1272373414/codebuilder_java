package com.velocity.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.velocity.entity.ColumnModel;
import com.velocity.entity.DataBaseModel;
import com.velocity.entity.GriditemParam;
import com.velocity.entity.OtherMethod;
import com.velocity.entity.TableModel;
import com.velocity.utils.EntityUtils;
import com.velocity.utils.StringUtilsExt;

@SuppressWarnings("unchecked")
public class GeneratorVelocityHelper {
	/**
	 * xml配置参数信息
	 */
	@SuppressWarnings("rawtypes")
	public static Map xmlParams = new HashMap();

	/**配置文件路径*/
	private static String configFilePath = "database_config.xml";

	/**
	 * 资源参数
	 */
	public static DataBaseModel databaseParam;

	private GeneratorVelocityHelper() {
	}

	/*
	 * 
	 * 
	 * {pkName=id, createtime=, author=, injectClassName=mwtTruckGpsLog,
	 * className=MwtTruckGpsLog, jspPath=company/branchCompany,
	 * jdbc_url=jdbc:mysql://192.168.31.235:3306/pk_member?useUnicode=true&
	 * characterEncoding=UTF-8, basepackage=com.kz.business,
	 * tableName=mwt_truck_gps_log, modelName=business,
	 * outRoot=./generator-output, jdbc_password=123456,
	 * jdbc_driver=com.mysql.jdbc.Driver, describe=分公司绑定记录,
	 * dataBaseName=pk_member, projectName=pk-member-app, jdbc_username=root,
	 * controllerRequestUrl=/company/branchCompany.do}
	 */

	static {
		if (databaseParam == null) {
			databaseParam = new DataBaseModel();
			initParam();
		}
	}

	/**
	 * 初始化参数
	 */
	private static void initParam() {
		// 1.从配置文件中读取配置信息
		String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString().replace("file:/", "") + configFilePath;
		getParamFromConfigFile(filePath);

		// 2.从数据库中获取信息

		String url = StringUtilsExt.trim(databaseParam.jdbc_url);
		String username = StringUtilsExt.trim(databaseParam.jdbc_username);
		String password = StringUtilsExt.trim(databaseParam.jdbc_password);
		String dataBaseName = StringUtilsExt.trim(databaseParam.dataBaseName);
		Connection conn = DataBaseHelper.getConnection(url, username, password);

		List<TableModel> tableModelList = EntityUtils.getDataFromDatabase(conn, dataBaseName);
		databaseParam.tableList = tableModelList;
	}

	/**
	 * 从配置文件中读取配置信息
	 * @param xmlpath XML文件路径
	 */
	@SuppressWarnings("rawtypes")
	private static void getParamFromConfigFile(String xmlpath) {
		try {
			SAXBuilder saxbuilder = new SAXBuilder();
			Document document = saxbuilder.build(new File(xmlpath));
			Element rootElement = document.getRootElement();
			List list = rootElement.getChildren();

			Class<? extends DataBaseModel> databaseClass = databaseParam.getClass();
			Field[] databaseFields = databaseClass.getFields();

			for (int i = 0; i < list.size(); i++) {
				Element tempEle = (Element) list.get(i);
				tempEle.getText();
				String key = StringUtilsExt.trim(tempEle.getAttributeValue("key"));
				String value = StringUtilsExt.trim(tempEle.getText());
				xmlParams.put(key, value);

				for (Field field : databaseFields) {
					String fieldName = field.getName();
					if (fieldName.equals(key)) {
						field.set(databaseParam, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成JAVA代码文件
	 * @param inPath  源路径
	 * @param outPath 输出路径
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void outPutFile(String inPath, String outPath, List<ColumnModel> columnList) throws Exception {
		Properties properties = new Properties();
		properties.setProperty("resource.loader", "class");// 设置velocity资源加载方式为class
		properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"); // 设置velocity资源加载方式为file时的处理类
		VelocityEngine velocityEngine = new VelocityEngine(properties); // 实例化一个VelocityEngine对象
		VelocityContext context = new VelocityContext();// 实例化一个VelocityContext
		Iterator ite = FileHelper.paramLibrary.entrySet().iterator();
		while (ite.hasNext()) {
			Entry entry = (Entry) ite.next();
			String key = StringUtilsExt.trim(entry.getKey());
			String val = StringUtilsExt.trim(entry.getValue());
			context.put(key, val);
		}
//		String url = StringUtilsExt.trim(xmlParams.get("jdbc_url"));
//		String username = StringUtilsExt.trim(xmlParams.get("jdbc_username"));
//		String password = StringUtilsExt.trim(xmlParams.get("jdbc_password"));
//		String dataBaseName = StringUtilsExt.trim(xmlParams.get("dataBaseName"));
//		Connection conn = DataBaseHelper.getConnection(url, username, password);
		// 向VelocityContext中放入键值
		context.put("columnList", columnList);
		// 实例化一个StringWriter
		StringWriter writer = new StringWriter();
		velocityEngine.mergeTemplate(inPath, "UTF-8", context, writer);
		String result = null;
		if (outPath.lastIndexOf(".jsp") > 0) {
			result = writer.toString().replaceAll("\\\\", "");
		} else {
			result = writer.toString();
		}
		System.out.println(result);
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(new File(outPath), true), "UTF-8");
		fw.append(result);
		fw.flush();
		fw.close();
	}

	/**
	 * 构造gird页面参数list
	 * @param gridparams 结果集合
	 * @param key 参数名
	 * @param val 参数值
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static void bulidGridparams(List gridparams, String key, String val) {
		if ("grid_colnames".equals(key) || "grid_names".equals(key) || "grid_coltypes".equals(key)) {
			if (gridparams != null && gridparams.size() > 0) {
				for (int i = 0; i < gridparams.size(); i++) {
					GriditemParam gp = (GriditemParam) gridparams.get(i);
					if ("grid_colnames".equals(key)) {
						String vals[] = val.split(",");
						gp.setColname(vals[i]);
					} else if ("grid_names".equals(key)) {
						String vals[] = val.split(",");
						gp.setName(vals[i]);
					} else if ("grid_coltypes".equals(key)) {
						String vals[] = val.split(",");
						gp.setColtype(vals[i]);
					}
				}
			} else {
				String vals[] = val.split(",");
				if (vals != null && vals.length > 0) {
					for (int i = 0; i < vals.length; i++) {
						GriditemParam gp = new GriditemParam();
						if ("grid_colnames".equals(key)) {
							gp.setColname(vals[i]);
						} else if ("grid_names".equals(key)) {
							gp.setName(vals[i]);
						} else if ("grid_coltypes".equals(key)) {
							gp.setColtype(vals[i]);
						}
						gridparams.add(gp);
					}
				}

			}
		}
	}

	/**
	 * 构造其他方法配置信息list
	 * @param othermethods 结果集合
	 * @param key 参数名
	 * @param val 参数值
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static void bulidOtherMethods(List othermethods, String key, String val) {
		if ("otherTableName".equals(key) || "otherPkName".equals(key) || "otherMethodSuffix".equals(key) || "otherMethodName".equals(key)) {
			if (othermethods != null && othermethods.size() > 0) {
				for (int i = 0; i < othermethods.size(); i++) {
					OtherMethod om = (OtherMethod) othermethods.get(i);
					if ("otherTableName".equals(key)) {
						String vals[] = val.split(",");
						om.setOtherTableName(vals[i]);
					} else if ("otherPkName".equals(key)) {
						String vals[] = val.split(",");
						om.setOtherPkName(vals[i]);
					} else if ("otherMethodSuffix".equals(key)) {
						String vals[] = val.split(",");
						om.setOtherMethodSuffix(vals[i]);
					} else if ("otherMethodName".equals(key)) {
						String vals[] = val.split(",");
						om.setOtherMethodName(vals[i]);
					}
				}
			} else {
				String vals[] = val.split(",");
				if (vals != null && vals.length > 0) {
					for (int i = 0; i < vals.length; i++) {
						OtherMethod om = new OtherMethod();
						if ("otherTableName".equals(key)) {
							om.setOtherTableName(vals[i]);
						} else if ("otherPkName".equals(key)) {
							om.setOtherPkName(vals[i]);
						} else if ("otherMethodSuffix".equals(key)) {
							om.setOtherMethodSuffix(vals[i]);
						} else if ("otherMethodName".equals(key)) {
							om.setOtherMethodName(vals[i]);
						}
						othermethods.add(om);
					}
				}

			}
		}
	}
}
