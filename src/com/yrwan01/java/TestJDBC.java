package com.yrwan01.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

public class TestJDBC {
	@Test
	public void testJdbc() {
		String driver = "oracle.jdbc.OracleDriver"; // ������ʶ��
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // �����ַ���
		// String url ="jdbc:oracle:thin:@10.0.30.64:1521:orcl"; // ����Զ�̵����ݿ������ôд
		String user = "scott"; // ���ݿ���û���
		String password = "tiger"; // ���ݿ������
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from employees";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int empno = rs.getInt("employee_id");
				String ename = rs.getString("last_name");
				double sal = rs.getDouble("salary");
				Date hiredate = rs.getDate("hire_date");
				int deptno = rs.getInt(("department_id"));
				System.out.println(empno + "\t" + ename + "\t" + sal + "\t" + hiredate + "\t" + deptno);
			}

			flag = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// �ر�ִ��ͨ��
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// �ر�����ͨ��
			try {
				if (con != null && (!con.isClosed())) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			System.out.println("ִ�гɹ���");
		} else {
			System.out.println("ִ��ʧ�ܣ�");
		}
	}
}
