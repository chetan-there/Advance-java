package com.ct.day1;

import java.sql.*;
import java.util.Scanner;

public class FetchEmployeeDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Oracle XE default
        String user = "chetan"; // Your Oracle username
        String password = "oracle24"; // Your Oracle password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("✅ Connected to Oracle database successfully!");

            while (true) {
                System.out.println("\n===== Employee Management Menu =====");
                System.out.println("1. Show employees with salary > 50,000");
                System.out.println("2. Show all employee details");
                System.out.println("3. Insert 3 new employee details");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        String query = "SELECT * FROM employees WHERE salary > 50000";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(query)) {
                            System.out.println("\n👨‍💼 Employees with Salary > 50,000:");
                            while (rs.next()) {
                                System.out.printf("%-10d %-15s %-20s %-10.2f%n",
                                        rs.getInt("empid"),
                                        rs.getString("name"),
                                        rs.getString("designation"),
                                        rs.getDouble("salary"));
                            }
                        }
                    }

                    case 2 -> {
                        String query = "SELECT * FROM employees";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(query)) {
                            System.out.println("\n📋 All Employee Records:");
                            while (rs.next()) {
                                System.out.printf("%-10d %-15s %-20s %-10.2f%n",
                                        rs.getInt("empid"),
                                        rs.getString("name"),
                                        rs.getString("designation"),
                                        rs.getDouble("salary"));
                            }
                        }
                    }

                    case 3 -> {
                        String insert = "INSERT INTO employees (empid, name, designation, salary) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pst = conn.prepareStatement(insert)) {
                            for (int i = 1; i <= 3; i++) {
                                System.out.println("\n✏️ Enter details for Employee " + i);
                                System.out.print("EMPID: ");
                                int empid = sc.nextInt();
                                sc.nextLine(); // clear buffer

                                System.out.print("Name: ");
                                String name = sc.nextLine();

                                System.out.print("Designation: ");
                                String designation = sc.nextLine();

                                System.out.print("Salary: ");
                                double salary = sc.nextDouble();

                                pst.setInt(1, empid);
                                pst.setString(2, name);
                                pst.setString(3, designation);
                                pst.setDouble(4, salary);
                                pst.executeUpdate();
                            }
                            System.out.println("\n✅ 3 Employees inserted successfully!");
                        }
                    }

                    case 4 -> {
                        System.out.println("👋 Exiting... Have a great day!");
                        sc.close();
                        System.exit(0);
                    }

                    default -> System.out.println("❌ Invalid choice. Please try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }
}
