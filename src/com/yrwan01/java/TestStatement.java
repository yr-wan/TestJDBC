package com.yrwan01.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestStatement {

	/**
	 * 通过jdbc向数据表中插入记录
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStatement() {
		// 获取数据库连接
		Connection con = null;
		Statement s = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			// 准备插入的SQL语句
			String sql = "insert into testjdbc values (1,'AA','aa@163.com',to_date('1999-01-01','yyyy-mm-dd'))";
			// 获取statement对象
			s = con.createStatement();
			// 调用executeUpdate(sql)方法执行插入
			s.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭Statement对象
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 关闭Connection
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
