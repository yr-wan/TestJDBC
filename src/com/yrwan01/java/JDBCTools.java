package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 操作jdbc的工具类
 * 
 * @version 1.0
 */
public class JDBCTools {

	/**
	 * @param sql 待执行的SQL语句
	 * @throws Exception
	 */
	public void update(String sql) {
		Connection con = null;
		Statement s = null;
		try {
			con = JDBCTools.getConnection();
			s = con.createStatement();
			s.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(con, s);
		}
	}

	/**
	 * @param rs
	 * @param con
	 * @param s
	 */
	public static void release(ResultSet rs, Connection con, Statement s) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库资源
	 * 
	 * @param con
	 * @param s
	 */
	public static void release(Connection con, Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;

		InputStream is = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");

		Class.forName(driverClass);

		return DriverManager.getConnection(url, user, password);
	}

}
