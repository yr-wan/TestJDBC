package com.yrwan04.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

/**
 * 数据库连接池
 * 
 * @author Wyran
 *
 */
public class TestDataSource {
	/**
	 * 加载properties文件实现数据库连接池
	 * 调用BasicDataSourceFactory 的createDataSource方法创建BasicDataSource实例
	 */
	@Test
	public void testDBCPWithFactory() {
		BasicDataSource bds = null;
		try {
			InputStream is = TestDataSource.class.getClassLoader().getResourceAsStream("dbcp.properties");
			Properties properties = new Properties();
			properties.load(is);
			bds = BasicDataSourceFactory.createDataSource(properties);

			Connection con = bds.getConnection();
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bds != null) {
				try {
					bds.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用DBCP数据库连接池 
	 * 1.导入jar包：dbcp2 pool2 logging 
	 * 2.创建连接池
	 */
	@Test
	public void testDBCP() {
		BasicDataSource bds = null;
		try {
			// 创建DBCP数据源实例
			bds = new BasicDataSource();
			// 为数据源实例指定属性
			bds.setUsername("root");
			bds.setPassword("tiger");
			bds.setUrl("jdbc:mysql://localhost:3306/save_music?useSSL=false&serverTimezone=UTC");
			bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			// 指定数据源的可选属性
			bds.setInitialSize(10);// 初始化连接数:连接池启动时创建的初始化连接数量
			bds.setMaxTotal(50);// 最大活动连接:连接池在同一时间能够分配的最大活动连接的数量
			bds.setMinIdle(5);// 最小的空闲链接数量:连接池中容许保持空闲状态的最小连接数量,低于这个数量将创建新的连接
			bds.setMaxWaitMillis(5000);// 最大等待时间:当没有可用连接时,连接池等待连接被归还的最大时间,超过时间则抛出异常

			// 从数据源获取连接
			Connection con = bds.getConnection();
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bds != null) {
				try {
					bds.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
