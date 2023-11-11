package com.rahul.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rahul.model.Student;

public class TestApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class);
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		Student student = new Student();
		student.setSid(2);
		student.setName("Sanjay");
		student.setAge(43);
		student.setAddress("Nagar");

		session.save(student);

		transaction.commit();

		session.close();
		factory.close();

	}

}
