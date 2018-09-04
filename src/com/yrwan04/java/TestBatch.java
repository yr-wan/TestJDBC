package com.yrwan04.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

public class TestBatch {
	@Test
	public void testBatch() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;

		try {
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);
			sql = "INSERT INTO customers VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			Date date = new Date(new java.util.Date().getTime());

			long begin = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				preparedStatement.setInt(1, i + 1);
				preparedStatement.setString(2, "name_" + i);
				preparedStatement.setDate(3, date);

				// "����" SQL
				preparedStatement.addBatch();

				// �� "����" ��һ���̶�, ��ͳһ��ִ��һ��. ���������ǰ "����" �� SQL
				if ((i + 1) % 300 == 0) {
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}
			}

			// ������������������ֵ��������, ����Ҫ�ٶ����ִ��һ��.
			if (100000 % 300 != 0) {
				preparedStatement.executeBatch();
				preparedStatement.clearBatch();
			}

			long end = System.currentTimeMillis();

			System.out.println("Time: " + (end - begin)); // 569

			JDBCTools.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(connection);
		} finally {
			JDBCTools.release(null, preparedStatement, connection);
		}
	}

	@Test
	public void testBatchWithPreparedStatement() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;

		try {
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);
			sql = "INSERT INTO customers VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			Date date = new Date(new java.util.Date().getTime());

			long begin = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				preparedStatement.setInt(1, i + 1);
				preparedStatement.setString(2, "name_" + i);
				preparedStatement.setDate(3, date);

				preparedStatement.executeUpdate();
			}
			long end = System.currentTimeMillis();

			System.out.println("Time: " + (end - begin)); // 9819

			JDBCTools.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(connection);
		} finally {
			JDBCTools.release(null, preparedStatement, connection);
		}
	}

	/**
	 * �� Oracle �� customers ���ݱ��в��� 10 ������¼ ������β���, ��ʱ���. 1. ʹ�� Statement.
	 */
	@Test
	public void testBatchWithStatement() {
		Connection connection = null;
		Statement statement = null;
		String sql = null;

		try {
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);

			statement = connection.createStatement();

			long begin = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				sql = "INSERT INTO customers VALUES(" + (i + 1) + ", 'name_" + i + "', '29-6�� -13')";
				statement.addBatch(sql);
			}
			long end = System.currentTimeMillis();

			System.out.println("Time: " + (end - begin)); // 39567

			JDBCTools.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(connection);
		} finally {
			JDBCTools.release(null, statement, connection);
		}
	}
}
