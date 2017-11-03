package com.velocity.entity;

/**
 * jsp页面列参数
 * @author neo
 * @since 2013-01-01
 */
public class GriditemParam {
	/**
	 * 显示列中文名称
	 */
	private String colname;

	/**
	 * 列name
	 */
	private String name;

	/**
	 * 列类型
	 */
	private String coltype;

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getColtype() {
		return coltype;
	}

	public void setColtype(String coltype) {
		this.coltype = coltype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
