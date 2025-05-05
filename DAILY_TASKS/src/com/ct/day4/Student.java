package com.ct.day4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student {

	String studentId;
	String studentName;
	String course;
	int semester;
	String email;
	String phoneNumber;

	// Parameterized constructor
	public Student(String studentId, String studentName, String course, int semester, String email,
			String phoneNumber) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.course = course;
		this.semester = semester;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// Insert a student record into the database
	public void insertStudent(Student student) {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan",
				"oracle24")) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO student1 VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, student.studentId);
			ps.setString(2, student.studentName);
			ps.setString(3, student.course);
			ps.setInt(4, student.semester);
			ps.setString(5, student.email);
			ps.setString(6, student.phoneNumber);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Student details added successfully.");
			} else {
				System.out.println("Failed to add student details.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Fetch and display all students
	public void fetchAllStudents() {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan",
				"oracle24")) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student1");
			ResultSet rs = ps.executeQuery();

			System.out.printf("%-10s %-15s %-10s %-10s %-25s %-15s%n", "StudentID", "Name", "Course", "Semester",
					"Email", "Phone");
			System.out.println(
					"----------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.printf("%-10s %-15s %-10s %-10d %-25s %-15s%n", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Fetch a student by ID and display in table format
	public void fetchStudentById(String sId) {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan",
				"oracle24")) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student1 WHERE UPPER(studentId) = ?");
			ps.setString(1, sId.toUpperCase()); // Make sure to use uppercase to match the stored studentId
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.printf("%-10s %-15s %-10s %-10s %-25s %-15s%n", "StudentID", "Name", "Course", "Semester",
						"Email", "Phone");
				System.out.println(
						"----------------------------------------------------------------------------------------------");

				System.out.printf("%-10s %-15s %-10s %-10d %-25s %-15s%n", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			} else {
				System.out.println("No student found with ID: " + sId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update a student's email by ID
	public void updateStudentEmail(String studentId, String newEmail) {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan",
				"oracle24")) {
			PreparedStatement ps = conn.prepareStatement("UPDATE student1 SET email = ? WHERE UPPER(studentId) = ?");
			ps.setString(1, newEmail);
			ps.setString(2, studentId.toUpperCase()); // Ensure the student ID is in uppercase

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Email updated successfully for student ID: " + studentId);
			} else {
				System.out.println("Failed to update email. Student ID not found.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete a student by ID
	public void deleteStudentById(String studentId) {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan",
				"oracle24")) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM student1 WHERE UPPER(studentId) = ?");
			ps.setString(1, studentId.toUpperCase()); // Ensure the student ID is in uppercase

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Student with ID " + studentId + " deleted successfully.");
			} else {
				System.out.println("Deletion failed. Student ID not found.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
