package com.yrwan01.exer;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

/**
 * 插入一个新的 student 信息
 * 请输入考生的详细信息
 * FlowID:
 * Type: 
 * IDCard:
 * ExamCard:
 * StudentName:
 * Location:
 * Grade:
 * 信息录入成功!
 * @author Wyran
 *
 */
public class test02 {
	@Test
	public void testAddNewStudent() throws Exception{
		Student student = getStudent();
		addNewStudent(student);
	}
	
	/**
	 * 从控制台录入学生信息
	 * @return
	 */
	public static Student getStudent(){
		System.out.println("请输入考生的详细信息");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("FlowID:");
		int FlowID = sc.nextInt();
		System.out.print("Type:");
		int Type = sc.nextInt();
		System.out.print("IDCard:");
		String IDCard = sc.next();
		System.out.print("ExamCard:");
		String ExamCard = sc.next();
		System.out.print("StudentName:");
		String StudentName = sc.next();
		System.out.print("Location:");
		String Location = sc.next();
		System.out.print("Grade:");
		int Grade = sc.nextInt();
		sc.close();
		return new Student(FlowID, Type, IDCard, ExamCard, StudentName, Location, Grade);
	} 
	
	/**
	 * 将学生插入到数据库
	 * @param student 待插入的学生对象
	 * @throws Exception
	 */
	public static void addNewStudent(Student student) throws Exception{
		// 将学生插入到数据库
		Connection con = test01.getConnection();
		Statement s = con.createStatement();
		String sql = "INSERT INTO examstudent VALUES ("
				+ student.getFlowID() +"," 
				+ student.getType() +",'" 
				+ student.getIDCard() + "','" 
				+ student.getExamCard() + "','" 
				+ student.getStudentName() + "','" 
				+ student.getLocation() + "'," 
				+ student.getGrade() + ")";
		s.executeUpdate(sql);
		// 提示
		System.out.println("信息录入成功!");
		
		s.close();
		con.close();
	}
}

class Student{
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