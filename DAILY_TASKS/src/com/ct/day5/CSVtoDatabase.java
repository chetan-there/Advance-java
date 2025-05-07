package com.ct.day5;

import java.io.*;
import java.sql.*;

public class CSVtoDatabase {
    public static void main(String[] args) {
        String csvPath = "D:/emp_data.csv"; // CSV file path
       

        try (
        		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
    			BufferedReader br = new BufferedReader(new FileReader(csvPath));
        ) {
            String line;
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Emp_Info VALUES (?, ?, ?, ?, ?)"
            );

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                ps.setInt(1, Integer.parseInt(data[0]));
                ps.setString(2, data[1]);
                ps.setString(3, data[2]);
                ps.setString(4, data[3]);
                ps.setString(5, data[4]);

                ps.executeUpdate();
            }

            System.out.println("CSV data inserted into database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
