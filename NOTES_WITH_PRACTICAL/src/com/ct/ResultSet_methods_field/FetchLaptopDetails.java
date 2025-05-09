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

Types of ResultSet based in direction of movement:
-----------------------------------------------------
  1.  NonScrollable ResultSet:
  -----------------------------
     - It is a simple ResultSet, which moves forward
       through the data.
     - It can't move backward or not even in random order.
        * You can move forward to the next row by using next().
    * You cant move backwards by using previous() in case of NonScrollable
      ResultSet.
     - It is best for the situation where we have to read the data only once
       from top to bottom.
     
  - Scrollable ResultSet:
  -----------------------------
  It helps to move cursor in both direction i.e, in forward as well as in
  background direction.

  Syntax for Scrollable ResultSet:
  ----------------------------------
        Statement refVariable = connectionreference.createStatement
                            (type,mode);
  e.g:
      Statement s1 = con.createStatement
                     (ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
      ResultSet rSet = s1.executeQuery("select * from laptop");
      In the above syntax:
      'type' will specify direction of movement.
      'mode' will specify about the operation/action in ResultSet.
Note:
   For specifying the type we use the following fields/variables from
   java.sql.ResultSet:
       -  public static final int TYPE_FORWARD_ONLY;
       -  public static final int TYPE_SCROLL_INSENSITIVE;
       -  public static final int TYPE_SCROLL_SENSITIVE;
   
    For specifying the mode we use the following fields/variables from
    java.sql.ResultSet:
          - public static final int CONCUR_READ_ONLY;
          - public static final int CONCUR_UPDATABLE;
----------------------------------------------------------------------------
Types based on Scrolling:
----------------------------

           Type                                               Meaning
i. ResultSet.TYPE_FORWARD_ONLY             The cursor can only move forward
                                          (default type).

ii. ResultSet.TYPE_SCROLL_INSENSITIVE      Cursor can move forward and backward.
                                          It does not reflect changes made to
                      the database after the ResultSet was
                      created.

iii. ResultSet.TYPE_SCROLL_SENSITIVE    Cursor can move forward and backward,
                                        and reflects changes made in the
                    database after the ResultSet was
                    created.

 
 */

