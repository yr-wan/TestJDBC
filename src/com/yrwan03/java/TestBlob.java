package com.yrwan03.java;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Test;

public class TestBlob {
	@Test
	public void createTable() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/save_music?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "tiger";
		String sql = "create table save_music(id int, music Mediumblob)";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();

		ps.close();
		con.close();
	}

	@Test
	public void insertMusic() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/save_music?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "tiger";
		String sql = "insert into save_music values(?,?)";

		InputStream is = new FileInputStream("D:/1.mp3");

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 1);
		ps.setBlob(2, is);
		ps.executeUpdate();

		ps.close();
		con.close();
	}
}
