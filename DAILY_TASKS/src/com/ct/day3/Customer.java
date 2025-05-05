package com.ct.day3;


import java.sql.*;

public class Customer {

		String customerId;
		String customerName;
		String email;
		String phoneNumber;
		String address;
		String city;
		int pincode;
		
		public void insertCustomer(String customerId, String customerName, String email, String phoneNumber, String address, String city, int pincode){
			this.customerId=customerId;
			this.customerName=customerName;
			this.email=email;
			this.phoneNumber=phoneNumber;
			this.address=address;
			this.city=city;
			this.pincode=pincode;
			
			try {
				Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
				PreparedStatement ps = conn.prepareStatement("INSERT INTO CustomerTable VALUES(?,?,?,?,?,?,?)");
				ps.setString(1, customerId);
				ps.setString(2, customerName);
				ps.setString(3, email);
				ps.setString(4, phoneNumber);
				ps.setString(5, address);
				ps.setString(6, city);
				ps.setInt(7, pincode);
				
				int k = ps.executeUpdate();
				if(k>0) {
					System.out.println("Customer Added Successfully....!");
				}else {
					System.out.println("No Added....!");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		public void displayCustomerByCity(String city) {
			try {
				Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
				PreparedStatement ps = conn.prepareStatement("select * from CustomerTable where CCITY =  ?");
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getInt(7));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

	

}
