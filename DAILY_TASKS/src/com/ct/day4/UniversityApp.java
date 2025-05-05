package com.ct.day4;

import java.util.Scanner;

public class UniversityApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Student studentOps = new Student("dummy", "dummy", "dummy", 0, "dummy", "dummy"); // Just for method calls

		while (true) {
			System.out.println("\nMenu:");
			System.out.println("1. Insert student");
			System.out.println("2. Display all students");
			System.out.println("3. Search student by ID");
			System.out.println("4. Update student email");
			System.out.println("5. Delete student by ID");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");

			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
				case 1:
					System.out.print("How many students to add? ");
					int count = Integer.parseInt(sc.nextLine());
					for (int i = 0; i < count; i++) {
						System.out.print("ID: ");
						String id = sc.nextLine().toUpperCase(); // Force input to uppercase
						System.out.print("Name: ");
						String name = sc.nextLine();
						System.out.print("Course: ");
						String course = sc.nextLine();
						System.out.print("Semester: ");
						int sem = Integer.parseInt(sc.nextLine());
						System.out.print("Email: ");
						String email = sc.nextLine();
						System.out.print("Phone: ");
						String phone = sc.nextLine();

						Student s1 = new Student(id, name, course, sem, email, phone);
						s1.insertStudent(s1);
					}
					break;

				case 2:
					studentOps.fetchAllStudents();
					break;

				case 3:
					System.out.print("Enter Student ID: ");
					String sid = sc.nextLine().toUpperCase(); // Force input to uppercase
					studentOps.fetchStudentById(sid);
					break;

				case 4:
					System.out.print("Enter Student ID to update email: ");
					String sid2 = sc.nextLine().toUpperCase(); // Force input to uppercase
					System.out.print("Enter new email: ");
					String newEmail = sc.nextLine();
					studentOps.updateStudentEmail(sid2, newEmail);
					break;

				case 5:
					System.out.print("Enter Student ID to delete: ");
					String sid3 = sc.nextLine().toUpperCase(); // Force input to uppercase
					studentOps.deleteStudentById(sid3);
					break;

				case 6:
					System.out.println("Exiting...");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid choice.");
			}
		}
	}
}
