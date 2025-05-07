package com.ct.day5;

import java.sql.*;
import java.util.Scanner;

public class StudentProcedureApp {
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);

        try (
        		Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
				 CallableStatement cs = conn.prepareCall("{call insertStudent(?,?,?,?,?,?,?,?,?)}");
        ) {
            // Taking user input
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Roll No: ");
            String roll = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();

            System.out.print("Enter House No: ");
            String hno = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter Pincode: ");
            String pincode = sc.nextLine();

            System.out.print("Enter Email ID: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone No: ");
            String phone = sc.nextLine();

            // Setting values to the procedure
            cs.setInt(1, id);
            cs.setString(2, roll);
            cs.setString(3, name);
            cs.setString(4, branch);
            cs.setString(5, hno);
            cs.setString(6, city);
            cs.setString(7, pincode);
            cs.setString(8, email);
            cs.setString(9, phone);

            // Execute the procedure
            cs.execute();

            System.out.println("✅ Student details inserted successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Database error:");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
