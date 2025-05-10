package com.ct.topic_wise_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

	String productId;
	String productName;
	String category; // category can be Electronics,books,furniture
	int quantity;
	double price;
	String supplier; // any orgnization Name
	
	

	public Product(String productId, String productName, String category, int quantity, double price, String supplier) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.supplier = supplier;
	}



	public void insertProduct() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");

			PreparedStatement ps = con.prepareStatement("insert into ProductTable values(?,?,?,?,?,?)");

			ps.setString(1, productId);
			ps.setString(2, productName);
			ps.setString(3, category);
			ps.setInt(4, quantity);
			ps.setDouble(5, price);
			ps.setString(6, supplier);

			int rs = ps.executeUpdate();

			if (rs > 0) {
				System.out.println(" Product inseted into DB successfully");
			} else {
				System.out.println(" Product not inseted into DB , Try again !!!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void displayProductsByCategory(String category) {
		try {
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
			
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM ProductTable WHERE category= ?");
			ps1.setString(1, category);
			
			ResultSet rs1 = ps1.executeQuery();
			/*String productId;
			String productName;
			String category; // category can be Electronics,books,furniture
			int quantity;
			double price;
			String supplier;*/
			while(rs1.next()) {
				System.out.println("Product Details "+rs1.getString("productId")+" "+rs1.getString("productName")+" "+rs1.getString("category")+" "+rs1.getInt("quantity")+" "+rs1.getDouble("price")+" "+rs1.getString("supplier"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
