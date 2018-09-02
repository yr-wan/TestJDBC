package com.yrwan03.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO:Date Accsee Object 访问数据信息的类，包含了对数据的增删改查，而不包含任何业务相关的信息 容易实现功能的模块化
 * 
 * @author Wyran
 *
 */
public class TestDAO {
	// INSERT, UPDATE, DELETE 操作
	public void update(String sql, Object... args) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
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

	public <T> T get(Class<T> clazz, String sql, Object... args) {
		T entity = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				Map<String, Object> values = new HashMap<>();

				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String label = rsmd.getColumnLabel(i + 1);
					Object value = rs.getObject(i + 1);
					values.put(label, value);
				}

				entity = clazz.newInstance();

				for (Map.Entry<String, Object> entry : values.entrySet()) {
					String name = entry.getKey();
					Object value = entry.getValue();
					ReflectionUtils.setFieldValue(entity, name, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return entity;
	}
}
