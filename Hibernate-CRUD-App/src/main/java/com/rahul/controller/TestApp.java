package com.rahul.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.rahul.model.Student;
import com.rahul.service.IStudentService;
import com.rahul.servicefactory.StudentServiceFactory;
import com.rahul.util.HibernateUtil;

public class TestApp {

	static {
		HibernateUtil.startUp();
	}

	private static IStudentService service = StudentServiceFactory.getStudent();

	public static void main(String[] args) throws IOException {

		while (true) {

			System.out.println("-----------------");
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.println("-----------------");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter you choice, press [1/2/3/4/5] :: ");
			int ch = sc.nextInt();

			switch (ch) {

			case 1 -> insertOperation();

			case 2 -> readOperation();

			case 3 -> updateOperation();

			case 4 -> deleteOperation();

			case 5 -> exitOperation();

			default -> System.out.println("Enter correct choice");

			}

		}

	}

	private static void exitOperation() {
		System.out.println("EXITING FROM CRUD APPLICATION. PLEASE WAIT...");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("THANKS FOR USING HIBERNATE CRUD APPLCATION :)");
		System.exit(0);
	}

	private static void deleteOperation() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Student Id to delete the record :: ");
		int id = sc.nextInt();

		String status = service.delete(id);

		if (status.equalsIgnoreCase("success"))
			System.out.println("Record deleted succesfully for id :: " + id);
		else
			System.out.println("Record not available for given id :: " + id);
	}

	private static void updateOperation() throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the student id to update the data :: ");
		String id = sc.readLine();

		Student student = service.read(Integer.parseInt(id));

		if (student != null) {
			Student newStudent = new Student();

			System.out.println("Student ID is :: " + id);
			newStudent.setSid(student.getSid());

			System.out.println("Student old name is " + student.getSname() + " Enter new name :: ");
			String newName = sc.readLine();
			if (newName.equals("") || newName == "")
				newStudent.setSname(student.getSname());
			else
				newStudent.setSname(newName);

			System.out.println("Student old age is " + student.getSage() + " Enter new age :: ");
			String newAge = sc.readLine();
			if (newAge.equals("") || newAge == "")
				newStudent.setSage(student.getSage());
			else
				newStudent.setSage(Integer.parseInt(newAge));

			System.out.println("Student old address is " + student.getSaddress() + " Enter new address :: ");
			String newAddress = sc.readLine();
			if (newAddress.equals("") || newAddress == "")
				newStudent.setSaddress(student.getSaddress());
			else
				newStudent.setSaddress(newAddress);

			System.out.println("New Student data is :: " + newStudent);
			System.out.println();

			String msg = service.update(newStudent);
			if (msg.equalsIgnoreCase("success"))
				System.out.println("Record update succesfully...");
			else
				System.out.println("Record updation failed...");
		} else
			System.out.println("Student record not avaialable for the given id " + id + " for updation...");
	}

	private static void readOperation() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the id of Student to search :: ");
		int id = sc.nextInt();

		Student student = service.read(id);

		if (student != null) {
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			System.out.println(student.getSid() + "\t" + student.getSname() + "\t" + student.getSage() + "\t"
					+ student.getSaddress());
		} else
			System.out.println("Record not found for given id :: " + id);
	}

	private static void insertOperation() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Student Name :: ");
		String name = sc.next();

		System.out.println("Enter the Student Age :: ");
		int age = sc.nextInt();

		System.out.println("Enter the Student Address :: ");
		String address = sc.next();

		String status = service.insert(name, age, address);

		if (status.equalsIgnoreCase("success"))
			System.out.println("Record successfully saved to the database");
		else
			System.out.println("Record failed to save in database");
	}

}
