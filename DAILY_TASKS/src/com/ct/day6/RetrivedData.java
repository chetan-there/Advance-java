package com.ct.day6;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class RetrivedData {

	public static void callProcedure(Connection con) {
		Scanner sc = new Scanner(System.in);

		try {
			CallableStatement cs = con.prepareCall("{call  RetrievePatient(?,?,?,?,?,?,?,?)}");
			System.out.println("Enter ID:");
			int id = sc.nextInt();

			cs.setInt(1, id);

			cs.registerOutParameter(2, Types.CHAR);
			cs.registerOutParameter(3, Types.CHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.registerOutParameter(5, Types.CHAR);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.registerOutParameter(7, Types.CHAR);
			cs.registerOutParameter(8, Types.DOUBLE);

			cs.execute();

			System.out.println("Id=" + id);
			System.out.println("Name =" + cs.getString(2));

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