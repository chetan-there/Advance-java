package com.ct.day2;

import java.sql.*;
import java.util.Scanner;

public class Register_Login {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {

			// Step 2: Establish connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chetan", "oracle24");

			System.out.println("1. Register\n2. Login");
			System.out.print("Select Your Option: ");
			int option = Integer.parseInt(sc.nextLine());

			switch (option) {

			case 1:
				System.out.println("You are going for Registration");
				System.out.print("Enter Roll Number: ");
				int rno = Integer.parseInt(sc.nextLine());

				System.out.print("Enter Name: ");
				String name = sc.nextLine();

				System.out.print("Enter Department: ");
				String dept = sc.nextLine();

				System.out.print("Enter Year: ");
				int year = Integer.parseInt(sc.nextLine());

				System.out.print("Enter Percentage: ");
				double perc = Double.parseDouble(sc.nextLine());

				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO student (ROLLNUMBER, NAME, DEPARTMENT, YEAR, PERCENTAGE) VALUES (?, ?, ?, ?, ?)");
				ps.setInt(1, rno);
				ps.setString(2, name);
				ps.setString(3, dept);
				ps.setInt(4, year);
				ps.setDouble(5, perc);

				int k = ps.executeUpdate();
				if (k > 0) {
					System.out.println("Student Registration Successful");
				} else {
					System.out.println("Registration Failed");
				}
				break;

			case 2:
				System.out.println("You are going for Login");
				System.out.print("Enter Roll Number: ");
				int rno1 = Integer.parseInt(sc.nextLine());

				System.out.print("Enter Name: ");
				String name1 = sc.nextLine();

				PreparedStatement ps1 = con.prepareStatement("SELECT * FROM student WHERE ROLLNUMBER = ? AND NAME = ?");
				ps1.setInt(1, rno1);
				ps1.setString(2, name1);

				ResultSet rs = ps1.executeQuery();
				if (rs.next()) {
					System.out.println("Login Successful");
					System.out.println("Welcome " + rs.getString("NAME"));
					System.out.println(
							"1. Show students with > 60%\n" + "2. Update Department and Year based on Roll Number\n"
									+ "3. Delete student by Roll Number\n" + "4. Count of students with > 80%");
				} else {
					System.out.println("Login Failed");
				}
				break;

			default:
				System.out.println("Please select a valid option (1 or 2)");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
