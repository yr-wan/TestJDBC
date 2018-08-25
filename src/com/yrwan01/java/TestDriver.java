package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import org.junit.Test;

public class TestDriver {
	/**
	 * Driver��һ���ӿ�,ͨ��Driverʵ����Ķ����ȡ���ݿ�����
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDriver() throws Exception {
		// ����Driverʵ����Ķ���
		// �׶ˣ������������ݿ�ʱ��ͨ��
		Driver driver = new oracle.jdbc.driver.OracleDriver();
		// ׼��������Ϣ
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Properties info = new Properties();
		info.put("user", "scott");
		info.put("password", "tiger");
		// ����connect������ȡ����
		Connection con = driver.connect(url, info);
		System.out.println(con);

		con.close();
	}

	/**
	 * ͨ�õ�Driver�ӿ����ӷ��� ���������ݿ�����Driverʵ�����������url��user��password����һ�������ļ�
	 * ͨ���޸������ļ�ʵ�������ݿ�Ľ���
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDriver1() throws Exception {
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
		// �������ݿ���������
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		// ����connect������ȡ����
		Connection con = driver.connect(url, info);
		System.out.println(con);

		con.close();
	}
}