package com.yrwan02.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 与Statement相比，提高了可读性、性能，并且可以防止SQL注入
 * 
 * @author Wyran
 *
 */
public class TestPreparedStatement {
	@Test
	public void preparedStatement() {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			String sql = "INSERT INTO examstudent(FlowID, Type, IDCard, " + "ExamCard, StudentName, Location, Grade) "
					+ "VALUES(?,?,?,?,?,?,?)";

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);

			ps.setInt(1, 66);
			ps.setInt(2, 6);
			ps.setString(3, "123456");
			ps.setString(4, "987654321");
			ps.setString(5, "Tom");
			ps.setString(6, "BeiJing");
			ps.setInt(7, 99);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	}
}
