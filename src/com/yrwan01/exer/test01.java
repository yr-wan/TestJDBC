package com.yrwan01.exer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 创立数据库表 examstudent，表结构如下： 
 * 字段名			说明		类型
 * FlowID		流水号	Int(11)
 * Type			四级／六级	Int(11)
 * IDCard		身份证号码	Varchar(18)
 * ExamCard		准考证号码	Varchar(15)
 * StudentName	学生姓名	Varchar(20)
 * Location		区域		Varchar(20)
 * Grade		成绩		Int(11)
 * @author Wyran
 *
 */
public class test01 {
	@Test
	public void testCreatTable(){
		creatTable();
	}
	public static void creatTable() {
		// 获取连接
		Connection con = null;
		Statement s = null;
		try {
			con = getConnection();
			s = con.createStatement();
			// SQL语句
			String SQL = "CREATE TABLE examstudent("
					+ "FlowID Number(11), "
					+ "Type Number(11), "
					+ "IDCard Varchar(18),"
					+ "ExamCard Varchar(15),"
					+ "StudentName Varchar(20),"
					+ "Location Varchar(20),"
					+ "Grade Number(11))";
			// 执行
			s.executeUpdate(SQL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 断开
			if(s != null){
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Connection getConnection() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
