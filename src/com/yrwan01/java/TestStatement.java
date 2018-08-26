package com.yrwan01.java;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

public class TestStatement {

	/**
	 * 通过jdbc向数据表中插入记录
	 * @throws Exception 
	 */
	@Test
	public void testStatement() throws Exception{
		TestDriverManager tdm = new TestDriverManager();
		// 获取数据库连接
		Connection con = tdm.getConnection();
		// 准备插入的SQL语句
		String sql = "insert into testjdbc values (1,'AA','aa@163.com',to_date('1999-01-01','yyyy-mm-dd'))";
		//执行插入
		// 获取statement对象
		Statement s = con.createStatement();
		//调用executeUpdate(sql)方法执行插入
		s.executeUpdate(sql);
		//关闭Statement对象
		s.close();
		//关闭Connection
		con.close();
	}
}
