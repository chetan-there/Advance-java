package com.ct.day5;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class RetrieveResume {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Step 1: Get input from user
		System.out.print("Enter Employee ID: ");
		int empId = sc.nextInt();
		sc.nextLine(); // consume newline

		System.out.print("Enter Employee Name: ");
		String empName = sc.nextLine();

		System.out.print("Enter Employee Phone Number: ");
		String empPhNo = sc.nextLine();

		System.out.print("Enter Resume File Path (e.g., C:\\\\Users\\\\ASUS\\\\Documents\\\\R resume.pdf): ");
		String filePath = sc.nextLine();

		String insertSQL = "INSERT INTO Employee (empId, empName, empPhNo, empResume) VALUES (?, ?, ?, ?)";

		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
				PreparedStatement ps = conn.prepareStatement(insertSQL);
				FileInputStream fis = new FileInputStream(filePath);) {
			ps.setInt(1, empId);
			ps.setString(2, empName);
			ps.setString(3, empPhNo);
			ps.setBinaryStream(4, fis, fis.available());

			int rows = ps.executeUpdate();
			System.out.println(rows + " employee record inserted with resume.");
		} catch (SQLException e) {
			System.out.println("SQL error: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Resume file not found at the given path.");
		} catch (IOException e) {
			System.out.println("Error reading the file.");
		} finally {
			sc.close();
		}
	}
}
