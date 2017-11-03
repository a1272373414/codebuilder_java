package com.velocity.helper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.velocity.entity.DataBaseModel;
import com.velocity.entity.TableModel;
import com.velocity.utils.BeanMapUtils;
import com.velocity.utils.FileModel;
import com.velocity.utils.StringUtil;

/**
 * 文件辅助类
 * @author tangj
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FileHelper {

	// private static String outputPath = "";
	/**
	 * 是否生成文件
	 */
	private static boolean IS_OPEN = true;

	/**
	 * 参数库
	 */
	public static Map<String, Object> paramLibrary;
	public static Map<String, Object> paramLibrary2;
	public static Map<String, Object> paramLibrary3;
	public static TableModel tableModel;

	static {
		Map<String, Object> pbMap2 = new HashMap<String, Object>();
		pbMap2.put(".vm", ".java");
		paramLibrary2 = pbMap2;

		Map<String, Object> pbMap3 = new HashMap<String, Object>();
		pbMap3.put(".", "/");
		paramLibrary3 = pbMap3;
	}

	private FileHelper() {
	}

	public static void start(File f, FileModel fileModel, int leval, TableModel tableModel) {
		// 准备参数库
		initParamLibrary(tableModel);
		System.out.println(paramLibrary.get("tableName"));

		// 开始替换变量
		tree(f, fileModel, leval);
	}

	/** 
	 * 递归目录
	 * @param f 要递归的目录结构File对象 
	 * @param leval：目录深度记录器
	 * @param fu 目录路径对象
	 */
	@SuppressWarnings("unused")
	public static void tree(File f, FileModel fileModel, int leval) {
		File[] childs = f.listFiles();
		if (childs == null || childs.length <= 0) {
			// 已经没有子元素了
			return;
		}

		for (int i = 0; i < childs.length; i++) {
			FileModel.setFileName(fileModel, childs[i].getName(), leval, 1);

			// 1.如果是文件
			if (!childs[i].isDirectory()) {
				String relativePath = "/" + FileModel.getRelativePath(fileModel);

				String sourceTempeletePath = "template_app" + relativePath;
				String[] relativePathSplit = relativePath.split("\\/");
				String fileName = relativePathSplit[relativePathSplit.length - 1];
				relativePathSplit[relativePathSplit.length - 1] = "";
				String outRoot = GeneratorVelocityHelper.databaseParam.outRoot + String.join("/", relativePathSplit);
				String outFilePath = GeneratorVelocityHelper.databaseParam.outRoot + relativePath;
				// System.out.println("模板文件路径:" + sourceTempeletePath);
				// System.out.println("输出文件目录:" + outRoot);

				if (IS_OPEN) {
					// 格式化文件名称
					outRoot = format(outRoot, "2");
					System.out.println("实际输出文件目录:" + outRoot);
					// 创建文件夹
					CreateFolder(outRoot);
					// System.out.println("原输出文件名称:" + fileName);
					// 格式化文件名称
					fileName = format(fileName, "1");
					// System.out.println("实际输出文件名称:" + fileName);
					try {
						// 格式化文件内容
						// GeneratorVelocityHelper.outPutFile(sourceTempeletePath,
						// outRoot + fileName,
						// FileHelper.tableModel.columnList);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// 2.如果是文件夹
			if (childs[i].isDirectory()) {
				tree(childs[i], fileModel, leval + 1);
			}
		}
	}

	/**
	 * 创建文件夹（根据路径级联创建，如果目录的上一级目录不存在则按路径创建）
	 * @param path
	 * @return
	 */
	public static boolean CreateFolder(String path) {
		StringBuffer returnStr = new StringBuffer();
		Boolean bool = false;
		// 根据符号"/"来分隔路径
		String[] paths = path.split("/");
		int length = paths.length;
		for (int i = 0; i < length; i++) {
			returnStr.append(paths[i]);
			File file = new File(returnStr.toString());
			if (!file.isDirectory()) {
				bool = file.mkdir();
			}
			returnStr.append("/");
		}
		return bool;
	}

	/**
	 * 初始化参数库
	 * @return
	 */
	public static void initParamLibrary(TableModel tableModel) {
		DataBaseModel dataBaseModel = (DataBaseModel) GeneratorVelocityHelper.databaseParam.clone();
		Map pbMap = BeanMapUtils.getBean2Map(dataBaseModel);
		Map tableMap = BeanMapUtils.getBean2Map(tableModel);
		pbMap.remove("tableList");
		tableMap.remove("columnList");
		pbMap.putAll(tableMap);
		paramLibrary = pbMap;

		FileHelper.tableModel = tableModel;
	}

	/**
	 * 格式化(替换其中的动态参数)
	 * @param oldName
	 * @param type 1:文件;2:文件夹
	 * @return
	 */
	public static String format(String oldName, String type) {
		String newName = oldName;
		newName = formatName(newName);
		if ("1".equals(type)) {
			newName = formatFileName(newName);
		} else if ("2".equals(type)) {
			newName = formatDirectoryName(newName);
		}
		return newName;
	}

	public static String formatName(String oldFileName) {
		String newFileName = oldFileName;
		Set<Entry<String, Object>> entrySet = paramLibrary.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			String value = StringUtil.trim(entry.getValue());
			newFileName = newFileName.replace("${" + key + "}", value);
		}
		return newFileName;
	}

	/**
	 * 格式化文件名(替换其中的动态参数)
	 * @param pb
	 * @param oldFileName
	 * @return
	 */
	public static String formatFileName(String oldFileName) {
		String newFileName = oldFileName;
		Set<Entry<String, Object>> entrySet = paramLibrary2.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			String value = StringUtil.trim(entry.getValue());
			newFileName = newFileName.replace(key, value);
		}
		return newFileName;
	}

	/**
	 * 格式化文件夹名(替换其中的动态参数)
	 * @param pb
	 * @param oldFileName
	 * @return
	 */
	public static String formatDirectoryName(String oldFileName) {
		String newFileName = oldFileName;
		Set<Entry<String, Object>> entrySet = paramLibrary3.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			String value = StringUtil.trim(entry.getValue());
			newFileName = newFileName.replace(key, value);
		}
		return newFileName;
	}

	public static void formatFileContent(DataBaseModel pb, String filename) {
	}
}