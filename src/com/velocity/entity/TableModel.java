package com.velocity.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 表对象
 * @author tangj
 * 
 */
public class TableModel {

	public String getTablePkname() {
		return tablePkname;
	}

	public void setTablePkname(String tablePkname) {
		this.tablePkname = tablePkname;
	}

	/**
	 * 表名称
	 */
	public String tableName;

	/**
	 * 类名称
	 */
	public String tableClassName;

	/**
	 * 注入类名称
	 */
	public String tableInjectClassName;

	/**
	 * 表主键列名称
	 */
	public String tablePkName;

	/**
	 * 表注释
	 */
	public String tableDescription;
	
	/**
	 * 主键列
	 */
	public String tablePkname;
	

	/**
	 * column列表
	 */
	public List<ColumnModel> columnList = new ArrayList<ColumnModel>();

	public List<ColumnModel> getColumnList() {
		return columnList;
	}

	public String getTableClassName() {
		return tableClassName;
	}

	public String getTableDescription() {
		return tableDescription;
	}

	public String getTableInjectClassName() {
		return tableInjectClassName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTablePkName() {
		return tablePkName;
	}

	public void setColumnList(List<ColumnModel> columnList) {
		this.columnList = columnList;
	}

	public void setTableClassName(String tableClassName) {
		this.tableClassName = tableClassName;
	}

	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}

	public void setTableInjectClassName(String tableInjectClassName) {
		this.tableInjectClassName = tableInjectClassName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTablePkName(String tablePkName) {
		this.tablePkName = tablePkName;
	}

	@Override
	public String toString() {
		return "TableModel [tableName=" + tableName + ", tableClassName=" + tableClassName + ", tableInjectClassName=" + tableInjectClassName + ", tablePkName=" + tablePkName + ", tableDescription=" + tableDescription + ", columnList=" + columnList + "]";
	}
	
	

}
