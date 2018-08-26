package com.yrwan01.java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import org.junit.Test;

public class TestResultSet {
	/**
	 * 
	 */
	@Test
	public void testResultSet() {
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = JDBCTools.getConnection();
			s = con.createStatement();
			String sql = "select * from testjdbc";
			rs = s.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				Date date = rs.getDate("HIRE_DATE");
				System.out.println(id + "\t" + name + "\t" + email + "\t" + date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(rs, con, s);
		}
	}
}
