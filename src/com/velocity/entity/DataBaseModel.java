package com.velocity.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库对象
 * @author tangj
 * 
 */
public class DataBaseModel extends BaseModel implements Cloneable {

	/* 从配置文件中读取 */
	/**
	 * 数据库名称
	 */
	public String dataBaseName;

	/**
	 * 账号
	 */
	public String jdbc_username;

	/**
	 * 密码
	 */
	public String jdbc_password;

	/**
	 * 数据库驱动
	 */
	public String jdbc_driver;

	/**
	 * 数据库URL
	 */
	public String jdbc_url;

	/**
	 * 输出根目录
	 */
	public String outRoot;

	/**
	 * 基础包名称
	 */
	public String basepackage;

	/**
	 * table列表
	 */
	public List<TableModel> tableList = new ArrayList<TableModel>();

	public String basepackage_dir;

	public String modelName;

	public String className;

	public String injectClassName;

	public String controllerRequestUrl;

	public String jspPath;

	@Override
	public Object clone() {
		DataBaseModel dm = null;
		try {
			dm = (DataBaseModel) super.clone(); // 浅复制
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dm;
	}

	public String getBasepackage() {
		return basepackage;
	}

	public String getBasepackage_dir() {
		return basepackage_dir;
	}

	public String getClassName() {
		return className;
	}

	public String getControllerRequestUrl() {
		return controllerRequestUrl;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public String getInjectClassName() {
		return injectClassName;
	}

	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public String getJdbc_url() {
		return jdbc_url;
	}

	public String getJdbc_username() {
		return jdbc_username;
	}

	public String getJspPath() {
		return jspPath;
	}

	public String getModelName() {
		return modelName;
	}

	public String getOutRoot() {
		return outRoot;
	}

	public List<TableModel> getTableList() {
		return tableList;
	}

	public void setBasepackage(String basepackage) {
		this.basepackage = basepackage;
	}

	public void setBasepackage_dir(String basepackage_dir) {
		this.basepackage_dir = basepackage_dir;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setControllerRequestUrl(String controllerRequestUrl) {
		this.controllerRequestUrl = controllerRequestUrl;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public void setInjectClassName(String injectClassName) {
		this.injectClassName = injectClassName;
	}

	/* 从配置文件中读取 */

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	public void setJdbc_username(String jdbc_username) {
		this.jdbc_username = jdbc_username;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setOutRoot(String outRoot) {
		this.outRoot = outRoot;
	}

	public void setTableList(List<TableModel> tableList) {
		this.tableList = tableList;
	}

	@Override
	public String toString() {
		return "DataBaseModel [dataBaseName=" + dataBaseName + ", jdbc_username=" + jdbc_username + ", jdbc_password=" + jdbc_password + ", jdbc_driver=" + jdbc_driver + ", jdbc_url=" + jdbc_url + ", outRoot=" + outRoot + ", basepackage=" + basepackage + ", tableList=" + tableList + ", basepackage_dir=" + basepackage_dir + ", modelName=" + modelName + ", className=" + className + ", injectClassName=" + injectClassName + ", controllerRequestUrl=" + controllerRequestUrl + ", jspPath=" + jspPath + "]";
	}

}
