package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class TestDriverManager {

	/**
	 * 通过驱动的管理类DriverManager来实现数据库的连接 
	 * 优点：可以同时管理多个驱动程序
	 * @throws Exception
	 */
	@Test
	public void testDriverManager() throws Exception {
		// 准备基本信息
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;
		// 读取路径下的jdbc.properties文件
		InputStream is = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// 加载数据库驱动程序（注册驱动）
		Class.forName(driverClass);
		// 调用DriverManager的getConnection方法获取连接
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println(con);

		con.close();
	}
	
	@Test
	public Connection getConnection() throws Exception {
		// 准备基本信息
		String driverClass = null;
		String url = null;
		String user = null;
		String password = null;
		// 读取路径下的jdbc.properties文件
		InputStream is = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// 加载数据库驱动程序（注册驱动）
		Class.forName(driverClass);
		// 调用DriverManager的getConnection方法获取连接
		return DriverManager.getConnection(url, user, password);
	}
}
