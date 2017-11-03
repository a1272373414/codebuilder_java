package com.velocity.entity;

/**
 * 列对象
 * @author tangj
 * 
 */
public class ColumnModel {

	/**
	 * 属性名
	 */
	public String property;

	/**
	 * 属性类型
	 */
	public String javaType;

	/**
	 * 方法名称
	 */
	public String methodName;

	/**
	 * 数据库列名称
	 */
	public String columnName;

	/**
	 * 数据库类型
	 */
	public String jdbcType;

	/**
	 * 列描述
	 */
	public String columnComment;

	public String getColumnComment() {
		return columnComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getJavaType() {
		return javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getProperty() {
		return property;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "ColumnModel [property=" + property + ", javaType=" + javaType + ", methodName=" + methodName + ", column=" + columnName + ", jdbcType=" + jdbcType + ", columnComment=" + columnComment + "]";
	}

}
