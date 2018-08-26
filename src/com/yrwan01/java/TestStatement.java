package com.yrwan01.java;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

public class TestStatement {

	/**
	 * ͨ��jdbc�����ݱ��в����¼
	 * @throws Exception 
	 */
	@Test
	public void testStatement() throws Exception{
		TestDriverManager tdm = new TestDriverManager();
		// ��ȡ���ݿ�����
		Connection con = tdm.getConnection();
		// ׼�������SQL���
		String sql = "insert into testjdbc values (1,'AA','aa@163.com',to_date('1999-01-01','yyyy-mm-dd'))";
		//ִ�в���
		// ��ȡstatement����
		Statement s = con.createStatement();
		//����executeUpdate(sql)����ִ�в���
		s.executeUpdate(sql);
		//�ر�Statement����
		s.close();
		//�ر�Connection
		con.close();
	}
}
