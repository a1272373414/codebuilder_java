package com.velocity.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库帮助类集合
 * @author neo
 * @since 2013-01-01
 */
public class DataBaseHelper {

	private static final String DEFAULT_URL = "jdbc:mysql://192.168.0.200/m_info?useUnicode=true&characterEncoding=UTF-8";

	private static final String DEFAULT_USE = "ms_info";

	private static final String DEFAULT_PWS = "ms_info";

	/**
	 * 加载驱动
	 * */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USE, DEFAULT_PWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 获取数据库连接
	 *
	 */
	public static Connection getConnection(String url, String username, String password) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭连接
	 * 
	 */
	public static void close(ResultSet rs, Statement stm, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stm != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
