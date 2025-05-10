package com.ct.transaction_management;

import java.sql.*;

import java.util.Scanner;

public class TransactionManagement {

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
				Scanner sc = new Scanner(System.in)) {

			con.setAutoCommit(false); // ✅ Start manual transaction mode

			PreparedStatement psmt = con.prepareStatement("SELECT * FROM BankAccount WHERE accNo = ?");
			PreparedStatement updateBal = con.prepareStatement("UPDATE BankAccount SET Balance = ? WHERE accNo = ?");

			System.out.print("Enter Source Account No: ");
			long srcAccNo = Long.parseLong(sc.nextLine());

			psmt.setLong(1, srcAccNo);
			ResultSet rsSrc = psmt.executeQuery();

			if (!rsSrc.next()) {
				System.out.println("❌ Invalid source account number.");
				return;
			}

			double srcBalance = rsSrc.getDouble("Balance");
			System.out.println("✅ Source Account Balance: " + srcBalance);

			System.out.print("Enter Destination Account No: ");
			long destAccNo = Long.parseLong(sc.nextLine());

			psmt.setLong(1, destAccNo);
			ResultSet rsDest = psmt.executeQuery();

			if (!rsDest.next()) {
				System.out.println("❌ Invalid destination account number.");
				return;
			}

			double destBalance = rsDest.getDouble("Balance");

			System.out.print("Enter amount to transfer: ");
			double amount = Double.parseDouble(sc.nextLine());

			if (amount <= 0 || amount > srcBalance) {
				System.out.println("❌ Insufficient funds or invalid amount.");
				return;
			}

			// ✅ Perform debit
			updateBal.setDouble(1, srcBalance - amount);
			updateBal.setLong(2, srcAccNo);
			updateBal.executeUpdate();

			// ✅ Perform credit
			updateBal.setDouble(1, destBalance + amount);
			updateBal.setLong(2, destAccNo);
			updateBal.executeUpdate();

			con.commit(); // ✅ Commit both operations
			System.out.println("✅ Amount transferred successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * 
 * > Output
 * 
 * Enter Source Account No: 101
 * ✅ Source Account Balance: 5000.5
 * Enter Destination Account No: 102
 * Enter amount to transfer: 2000
 * ✅ Amount transferred successfully.
 * 
 */
