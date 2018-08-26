package com.yrwan01.exer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * �������ݿ�� examstudent����ṹ���£� 
 * �ֶ���			˵��		����
 * FlowID		��ˮ��	Int(11)
 * Type			�ļ�������	Int(11)
 * IDCard		���֤����	Varchar(18)
 * ExamCard		׼��֤����	Varchar(15)
 * StudentName	ѧ������	Varchar(20)
 * Location		����		Varchar(20)
 * Grade		�ɼ�		Int(11)
 * @author Wyran
 *
 */
public class test01 {
	@Test
	public void testCreatTable(){
		creatTable();
	}
	public static void creatTable() {
		// ��ȡ����
		Connection con = null;
		Statement s = null;
		try {
			con = getConnection();
			s = con.createStatement();
			// SQL���
			String SQL = "CREATE TABLE examstudent("
					+ "FlowID Number(11), "
					+ "Type Number(11), "
					+ "IDCard Varchar(18),"
					+ "ExamCard Varchar(15),"
					+ "StudentName Varchar(20),"
					+ "Location Varchar(20),"
					+ "Grade Number(11))";
			// ִ��
			s.executeUpdate(SQL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// �Ͽ�
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
