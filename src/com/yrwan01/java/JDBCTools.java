package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ����jdbc�Ĺ�����
 * 
 * @version 1.0
 */
public class JDBCTools {

	/**
	 * @param sql ��ִ�е�SQL���
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
	 * �ر����ݿ���Դ
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
	 * ��ȡ���ݿ�����
	 * 
	 * @return ���ݿ�����
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
