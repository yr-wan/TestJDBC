package com.yrwan01.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import org.junit.Test;

public class TestDriver {
	/**
	 * Driver是一个接口,通过Driver实现类的对象获取数据库连接
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDriver() throws Exception {
		// 创建Driver实现类的对象
		// 弊端：连接其他数据库时不通用
		Driver driver = new oracle.jdbc.driver.OracleDriver();
		// 准备基本信息
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Properties info = new Properties();
		info.put("user", "scott");
		info.put("password", "tiger");
		// 调用connect方法获取连接
		Connection con = driver.connect(url, info);
		System.out.println(con);

		con.close();
	}

	/**
	 * 通用的Driver接口连接方法 即：将数据库驱动Driver实现类的类名、url、user、password放入一个配置文件
	 * 通过修改配置文件实现与数据库的解耦
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDriver1() throws Exception {
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
		// 加载数据库驱动程序
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		// 调用connect方法获取连接
		Connection con = driver.connect(url, info);
		System.out.println(con);

		con.close();
	}
}