package com.ct.ResultSet_methods_field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchLaptopDetails {

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM laptops", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

        	rs.first(); // Move cursor to after last row
//            while (rs.previous()) { // Iterate in reverse
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5));
//            }

        } catch (Exception e) {
            e.printStackTrace(); // Properly log the error
        }
    }
}
/* 

 */

