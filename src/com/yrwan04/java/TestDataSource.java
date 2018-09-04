package com.yrwan04.java;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

/**
 * ���ݿ����ӳ�
 * 
 * @author Wyran
 *
 */
public class TestDataSource {
	/**
	 * ����properties�ļ�ʵ�����ݿ����ӳ�
	 * ����BasicDataSourceFactory ��createDataSource��������BasicDataSourceʵ��
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
	 * ʹ��DBCP���ݿ����ӳ� 
	 * 1.����jar����dbcp2 pool2 logging 
	 * 2.�������ӳ�
	 */
	@Test
	public void testDBCP() {
		BasicDataSource bds = null;
		try {
			// ����DBCP����Դʵ��
			bds = new BasicDataSource();
			// Ϊ����Դʵ��ָ������
			bds.setUsername("root");
			bds.setPassword("tiger");
			bds.setUrl("jdbc:mysql://localhost:3306/save_music?useSSL=false&serverTimezone=UTC");
			bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			// ָ������Դ�Ŀ�ѡ����
			bds.setInitialSize(10);// ��ʼ��������:���ӳ�����ʱ�����ĳ�ʼ����������
			bds.setMaxTotal(50);// �������:���ӳ���ͬһʱ���ܹ������������ӵ�����
			bds.setMinIdle(5);// ��С�Ŀ�����������:���ӳ��������ֿ���״̬����С��������,������������������µ�����
			bds.setMaxWaitMillis(5000);// ���ȴ�ʱ��:��û�п�������ʱ,���ӳصȴ����ӱ��黹�����ʱ��,����ʱ�����׳��쳣

			// ������Դ��ȡ����
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
