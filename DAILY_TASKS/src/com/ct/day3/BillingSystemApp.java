package com.ct.day3;

import java.util.Scanner;

public class BillingSystemApp 
{
	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Add Customer");
		System.out.println("Press 2 for get Customer By City");
		int choice= Integer.parseInt(sc.nextLine());
		switch(choice){
		case 1:
			System.out.println("How many customer u Want to add");
			int count = Integer.parseInt(sc.nextLine());
			for(int i = 1;i<=count;i++)
			{
				System.out.println("Enter Customer ID");
				String id = sc.nextLine();
				System.out.println("Enter Customer Name");
				String name = sc.nextLine();
				System.out.println("Enter Customer email");
				String email = sc.nextLine();
				System.out.println("Enter Customer phoneNumber");
				String phno = sc.nextLine();
				System.out.println("Enter Customer address");
				String add = sc.nextLine();
				System.out.println("Enter Customer city");
				String city = sc.nextLine();
				System.out.println("Enter Customer pincode");
				int pin = Integer.parseInt(sc.nextLine());
				Customer cust = new Customer();
				cust.insertCustomer(id, name, email, phno, add, city, pin);
			}
			break;
		case 2:
			System.out.println("Enter Customer City");
			String c = sc.nextLine();
			new Customer().displayCustomerByCity(c);
		}
		sc.close();
	}
}
