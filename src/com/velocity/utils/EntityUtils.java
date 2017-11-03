package com.velocity.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velocity.entity.ColumnModel;
import com.velocity.entity.TableModel;

public class EntityUtils {

	/**
	 * 根据数据库列 获取 类字段名称('-'转驼峰命名)
	 * @param column
	 * @return
	 */
	private static String getAttrName(String column) {
		if (column.contains("_")) {
			String attrNameStr[] = column.split("_");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < attrNameStr.length; i++) {
				if (i == 0) {
					sb.append(attrNameStr[i].toLowerCase());
				} else {
					String str = attrNameStr[i].toLowerCase();
					if (str.length() > 1) {
						String beginStr = str.substring(0, 1);
						sb.append(beginStr.toUpperCase()).append(str.substring(1));
					} else {
						sb.append(str);
					}
				}
			}
			column = sb.toString();
		} else {
			column = column.toLowerCase();
		}
		// System.out.println(attrName);
		return column;
	}

	/**
	 * 根据数据库列 获取 类字段方法名称(后半段)
	 * 
	 * @param column
	 * @return
	 */
	private static String getMehodName(String column) {
		boolean flag = false;
		if (column.contains("_")) {
			String attrNameStr[] = column.split("_");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < attrNameStr.length; i++) {
				if (i == 0) {
					if (attrNameStr[i].length() > 1) {
						flag = true;
					}
					sb.append(attrNameStr[i].toLowerCase());
				} else {
					String str = attrNameStr[i].toLowerCase();
					if (str.length() > 1) {
						String beginStr = str.substring(0, 1);
						sb.append(beginStr.toUpperCase()).append(str.substring(1));
					} else {
						sb.append(str);
					}
				}
			}
			column = sb.toString();
		} else {
			column = column.toLowerCase();
			column = column.substring(0, 1).toUpperCase() + column.substring(1);
		}
		if (flag) {
			column = column.substring(0, 1).toUpperCase() + column.substring(1);
		}
		return column;
	}

	/**
	 * 根据数据库表名 获取 类名称('-'转驼峰命名)
	 * @param tableName
	 * @return
	 */
	private static String getTableClassName(String tableName) {
		if (tableName.contains("_")) {
			String attrNameStr[] = tableName.split("_");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < attrNameStr.length; i++) {
				String str = attrNameStr[i].toLowerCase();
				if (str.length() > 1) {
					String beginStr = str.substring(0, 1);
					sb.append(beginStr.toUpperCase()).append(str.substring(1));
				} else {
					sb.append(str);
				}
			}
			tableName = sb.toString();
		} else {
			tableName = tableName.toLowerCase();
		}
		return tableName;
	}

	// /**
	// * 创建DTO
	// *
	// * @param database
	// * @param tablename
	// */
	// @SuppressWarnings({ "unchecked", "unused", "static-access", "rawtypes" })
	// public static List<Entity> getCloumnToDTO(Connection connection, String
	// database, String tablename) {
	// List list = new ArrayList<ColumnModel>();
	// JdbcUtil jdbcdemo = new JdbcUtil();
	// Connection con = connection;
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// String sql = new StringBuffer().append("select
	// COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT from INFORMATION_SCHEMA.Columns
	// where table_name='").append(tablename).append("' and
	// table_schema='").append(database).append("'").toString();
	// try {
	// ps = con.prepareStatement(sql);
	// if (ps.execute()) {
	// rs = ps.getResultSet();
	// } else {
	// int i = ps.getUpdateCount();
	// }
	//
	// while (rs.next()) {
	// StringBuffer str = new StringBuffer();
	// String data_type = rs.getString("DATA_TYPE");
	// String type = "";
	//
	// if (data_type.equals("int") || data_type.equals("bigint")) {
	// type = "Long";
	// } else if (data_type.equals("varchar")) {
	// type = "String";
	// } else if (data_type.equals("decimal")) {
	// type = "Decimal";
	// } else if (data_type.equals("text")) {
	// type = "String";
	// } else if (data_type.equals("datetime")) {
	// type = "Date";
	// }
	// ColumnModel columnModel = new ColumnModel();
	// columnModel.setJdbcType(data_type);
	// columnModel.setJavaType(type);
	// columnModel.setColumn(rs.getString("COLUMN_NAME"));
	// columnModel.setProperty(getAttrName(rs.getString("COLUMN_NAME")));
	// columnModel.setMethodName(getMehodName(rs.getString("COLUMN_NAME")));
	// columnModel.setColumnComment(rs.getString("COLUMN_COMMENT"));
	// // System.out.println("Property:" +
	// // getAttrName(rs.getString("COLUMN_NAME")));
	// // System.out.println("MethodName:" +
	// // getMehodName(rs.getString("COLUMN_NAME")));
	// // System.out.println(rs.getString("COLUMN_COMMENT"));
	// list.add(columnModel);
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// jdbcdemo.close(rs, ps, con);
	// }
	// return list;
	// }

	/**
	 * 从数据库获取信息
	 * @param connection
	 * @param database
	 * @return
	 */
	public static List<TableModel> getDataFromDatabase(Connection connection, String database) {
		Connection con = connection;
		List<TableModel> tableModelList = getTableModelList(con, database);
		for (TableModel tableModel : tableModelList) {
			List<ColumnModel> columnModelList = getColumnModelList(con, database, tableModel.tableName);
			tableModel.columnList = columnModelList;
		}
		return tableModelList;
	}

	/**
	 * 根据数据库名,获取表名列表
	 * @param con
	 * @param database
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<TableModel> getTableModelList(Connection con, String database) {
		List<TableModel> list = new ArrayList<TableModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 用于查询当前库下的所有表名
		String sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.Tables WHERE TABLE_SCHEMA='" + database + "' GROUP BY TABLE_NAME";
		try {
			ps = con.prepareStatement(sql);
			if (ps.execute()) {
				rs = ps.getResultSet();
			} else {
				int i = ps.getUpdateCount();
			}
			while (rs.next()) {
				TableModel tableModel = new TableModel();
				String table_name = rs.getString("TABLE_NAME");
				String table_comment = rs.getString("TABLE_COMMENT");
				tableModel.tableName = table_name;
				tableModel.tableClassName = getTableClassName(table_name);
				tableModel.tableDescription = getTableClassName(table_comment);
				tableModel.tableInjectClassName = getAttrName(table_name);
				list.add(tableModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, null);
		}
		return list;
	}

	/**
	 * 根据表名,获取字段信息列表
	 * @param con
	 * @param database
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<ColumnModel> getColumnModelList(Connection con, String database, String tableName) {
		List<ColumnModel> list = new ArrayList<ColumnModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 用于查询表下面的列的信息
		String sql = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM INFORMATION_SCHEMA.Columns WHERE TABLE_NAME='" + tableName + "' AND TABLE_SCHEMA='" + database + "'";

		try {
			ps = con.prepareStatement(sql);
			if (ps.execute()) {
				rs = ps.getResultSet();
			} else {
				int i = ps.getUpdateCount();
			}
			while (rs.next()) {
				StringBuffer str = new StringBuffer();
				String jdbcType = rs.getString("DATA_TYPE");
				String javaType = "";

				if (jdbcType.equals("int") || jdbcType.equals("bigint")) {
					javaType = "Long";
				} else if (jdbcType.equals("varchar")) {
					javaType = "String";
				} else if (jdbcType.equals("decimal")) {
					javaType = "Decimal";
				} else if (jdbcType.equals("text")) {
					javaType = "String";
				} else if (jdbcType.equals("datetime")) {
					javaType = "Date";
				}
				ColumnModel columnModel = new ColumnModel();
				columnModel.jdbcType = jdbcType;
				columnModel.javaType = javaType;
				columnModel.columnName = rs.getString("COLUMN_NAME");
				columnModel.property = getAttrName(rs.getString("COLUMN_NAME"));
				columnModel.methodName = getMehodName(rs.getString("COLUMN_NAME"));
				columnModel.columnComment = rs.getString("COLUMN_COMMENT");
				list.add(columnModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, null);
		}
		return list;
	}
}
