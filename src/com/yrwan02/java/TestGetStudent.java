package com.yrwan02.java;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestGetStudent {
	@Test
	public void testResultSetMetaData() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			String sql = "SELECT FLOWID \"flowID\", TYPE \"type\", IDCARD \"iDCard\", "
					+ "EXAMCARD \"examCard\", STUDENTNAME \"studentName\", " + "LOCATION \"location\", GRADE \"grade\" "
					+ "FROM examstudent WHERE FLOWID = ?";
			// 创建连接
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			rs = ps.executeQuery();

			// 得到 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();

			// 创建一个Map对象
			Map<String, Object> values = new HashMap<String, Object>();

			// 利用ResultSetMetaDate的方法填充Map对象
			if (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					if(columnValue instanceof BigDecimal){
						columnValue = ((BigDecimal) columnValue).intValue();
					}
					values.put(columnLabel, columnValue);
				}
			}
			// 利用反射创建clazz对应的对象
			Class clazz = Student.class;
			Student object = (Student) clazz.newInstance();
			// 利用反射为对象对应的属性赋值
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();

				ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
			}

			System.out.println(object);

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
	}

	 @Test
	public void testGetStudent() {
		String sql = "SELECT * FROM examstudent WHERE flowID = ?";
		Student stu = getStudent(sql, 1);
		System.out.println(stu);
	}

	public static Student getStudent(String sql, Object... args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";

		Student student = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				student = new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
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
		return student;
	}
}

class Student {
	private int flowID;
	private int type;
	private String iDCard;
	private String examCard;
	private String studentName;
	private String location;
	private int grade;

	public Student(int flowID, int type, String iDCard, String examCard, String studentName, String location,
			int grade) {
		super();
		this.flowID = flowID;
		this.type = type;
		this.iDCard = iDCard;
		this.examCard = examCard;
		this.studentName = studentName;
		this.location = location;
		this.grade = grade;
	}

	public Student() {
		super();
	}

	public int getFlowID() {
		return flowID;
	}

	public void setFlowID(int flowID) {
		this.flowID = flowID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIDCard() {
		return iDCard;
	}

	public void setIDCard(String iDCard) {
		this.iDCard = iDCard;
	}

	public String getExamCard() {
		return examCard;
	}

	public void setExamCard(String examCard) {
		this.examCard = examCard;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [flowID=" + flowID + ", type=" + type + ", iDCard=" + iDCard + ", examCard=" + examCard
				+ ", studentName=" + studentName + ", location=" + location + ", grade=" + grade + "]";
	}
}