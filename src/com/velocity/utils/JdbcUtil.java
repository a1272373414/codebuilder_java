package com.velocity.utils;

import java.sql.*;

public class JdbcUtil {

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
	 * */
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
	 * 获取statement对象，操作数据库，处理返回结果
	 * */
	@SuppressWarnings("unused")
	public static void process() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from t_product";
		try {
			ps = con.prepareStatement(sql);
			if (ps.execute()) {
				rs = ps.getResultSet();
			} else {
				int i = ps.getUpdateCount();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
	}

	/**
	 * 处理返回结果集
	 * */
	public static void printResultSet(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			StringBuffer b = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					b.append(meta.getColumnName(i) + "=");
					b.append(rs.getString(i) + "/t");
				}
				b.append("/n");
			}
			System.out.print(b.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 * */
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
				stm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
