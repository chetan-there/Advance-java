package com.ct.day3;

import java.sql.*;
import java.util.Scanner;

public class ProductJDBCOracle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");

			while (true) {
				System.out.println("\n--- Product Menu ---");
				System.out.println("1. Insert Product");
				System.out.println("2. Display Products (Forward)");
				System.out.println("3. Display Products (Reverse)");
				System.out.println("4. 3rd Record From Top");
				System.out.println("5. 3rd Record From Bottom");
				System.out.println("6. Exit");
				System.out.print("Choose option: ");
				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					scanner.nextLine(); // consume newline
					System.out.print("Enter Product ID: ");
					String id = scanner.nextLine();
					System.out.print("Enter Product Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Product Price: ");
					double price = scanner.nextDouble();
					System.out.print("Enter Product Quantity: ");
					int qty = scanner.nextInt();

					pstmt = con.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
					pstmt.setString(1, id);
					pstmt.setString(2, name);
					pstmt.setDouble(3, price);
					pstmt.setInt(4, qty);
					int inserted = pstmt.executeUpdate();
					System.out.println(inserted + " product(s) inserted.");
					break;

				case 2:
					pstmt = con.prepareStatement("SELECT * FROM Product", ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();
					System.out.println("\n--- Product List (Forward) ---");
					while (rs.next()) {
						printProduct(rs);
					}
					break;

				case 3:
					pstmt = con.prepareStatement("SELECT * FROM Product", ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();
					rs.afterLast();
					System.out.println("\n--- Product List (Reverse) ---");
					while (rs.previous()) {
						printProduct(rs);
					}
					break;

				case 4:
					pstmt = con.prepareStatement("SELECT * FROM Product", ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();
					if (rs.absolute(3)) {
						System.out.println("\n--- 3rd Record From Top ---");
						printProduct(rs);
					} else {
						System.out.println("Less than 3 records found.");
					}
					break;

				case 5:
					pstmt = con.prepareStatement("SELECT * FROM Product", ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();
					rs.last();
					int rowCount = rs.getRow();
					if (rowCount >= 3 && rs.absolute(rowCount - 2)) {
						System.out.println("\n--- 3rd Record From Bottom ---");
						printProduct(rs);
					} else {
						System.out.println("Less than 3 records found.");
					}
					break;

				case 6:
					System.out.println("Program exited.");
					return;

				default:
					System.out.println("Invalid choice.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}

			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			scanner.close();
		}
	}

	private static void printProduct(ResultSet rs) throws SQLException {
		System.out.println("Product ID   : " + rs.getString("productId"));
		System.out.println("Product Name : " + rs.getString("productName"));
		System.out.println("Product Price: " + rs.getDouble("productPrice"));
		System.out.println("Product Qty  : " + rs.getInt("productQty"));
		System.out.println("----------------------------");
	}
}
