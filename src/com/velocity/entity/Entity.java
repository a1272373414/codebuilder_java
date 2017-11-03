package com.velocity.entity;

/**
 * 实体对象类
 * @author neo
 * @since 2013-01-01
 */
public class Entity {
	/**
	 * 属性名
	 */
	private String property;
	/**
	 * 属性类型
	 */
	private String javaType;

	/**
	 * 方法名称
	 */
	private String methodName;

	/**
	 * 数据库列名称
	 */
	private String column;

	/**
	 * 数据库类型
	 */
	private String jdbcType;

	/**
	 * 列描述
	 */
	private String columnComment;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
