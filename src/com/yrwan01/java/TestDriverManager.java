package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class TestDriverManager {

	/**
	 * ͨ�������Ĺ�����DriverManager��ʵ�����ݿ������ 
	 * �ŵ㣺����ͬʱ��������������
	 * @throws Exception
	 */
	@Test
	public void testDriverManager() throws Exception {
		// ׼��������Ϣ
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;
		// ��ȡ·���µ�jdbc.properties�ļ�
		InputStream is = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// �������ݿ���������ע��������
		Class.forName(driverClass);
		// ����DriverManager��getConnection������ȡ����
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println(con);

		con.close();
	}
	
	@Test
	public Connection getConnection() throws Exception {
		// ׼��������Ϣ
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;
		// ��ȡ·���µ�jdbc.properties�ļ�
		InputStream is = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// �������ݿ���������ע��������
		Class.forName(driverClass);
		// ����DriverManager��getConnection������ȡ����
		return DriverManager.getConnection(url, user, password);
	}
}
