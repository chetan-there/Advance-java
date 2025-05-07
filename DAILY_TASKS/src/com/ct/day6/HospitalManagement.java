package com.ct.day6;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagement {

	public static void callProcedure(Connection con) {

		try {
			CallableStatement cs = con.prepareCall("{call PatientDetails3(?,?,?,?,?,?,?,?)}");
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter Patient ID: ");
			int id = Integer.parseInt(sc.nextLine());

			System.out.println("Enter Patient Name: ");
			String name = sc.nextLine();

			System.out.println("Enter Patient Disease: ");
			String desease = sc.nextLine();

			System.out.println("Enter Patient House NO: ");
			int Hno = Integer.parseInt(sc.nextLine());

			System.out.println("Enter Parient City: ");
			String city = sc.nextLine();

			System.out.println("Enter Patient Pin NO: ");
			int pin = Integer.parseInt(sc.nextLine());	
			System.out.println("Enter Patient Email: ");
			String email = sc.nextLine();

			System.out.println("Enter Patient Phoen: ");
			long phone = Long.parseLong(sc.nextLine());

			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setString(3, desease);
			cs.setInt(4, Hno);
			cs.setString(5, city);
			cs.setInt(6, pin);
			cs.setString(7, email);
			cs.setLong(8, phone);

			boolean execute = cs.execute();

			
				System.out.println("SuccessFully Inserted.....");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
			
			callProcedure(con);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}