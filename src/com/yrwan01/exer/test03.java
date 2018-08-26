package com.yrwan01.exer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

/**
 * 在 eclipse 中建立 java 程序：输入身份证号或准考证号可以查询到学生的基本信息。结果如下：
 * 
 * @author Wyran
 *
 */
public class test03 {
	@Test
	public void testGetStudent() throws Exception {
		int type = getType();
		Student student = getStudent(type);
		printStudent(student);
	}

	/**
	 * 打印学生信息
	 * 
	 * @param student
	 */
	public static void printStudent(Student student) {
		if (student != null) {
			System.out.println(student.toString());
		} else {
			System.out.println("查无此人");
		}
	}

	/**
	 * 查询学生信息
	 * 
	 * @param type
	 * @throws Exception
	 */
	public static Student getStudent(int type) {
		Scanner sc = null;
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		Student student = null;
		try {
			sc = new Scanner(System.in);
			String sql = "SELECT * FROM examstudent WHERE ";

			if (type == 1) {
				System.out.print("请输入准考号：");
				String number = sc.next();
				sql = sql + "ExamCard = '" + number + "'";
			} else {
				System.out.print("请输入身份证号：");
				String number = sc.next();
				sql = sql + "IDCard = '" + number + "'";
			}

			con = test01.getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);

			if (rs.next()) {
				student = new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return student;
	}

	/**
	 * 获取输入类型 1.准考证号 2.身份证号
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	public static int getType() {
		System.out.println("请选择要输入的类型：1.准考证号 2.身份证号");
		Scanner sc = new Scanner(System.in);
		int type = sc.nextInt();
		if (type != 1 && type != 2) {
			System.out.println("输入类型有误");
			throw new RuntimeException("输入类型有误");
		}
		return type;
	}
}
