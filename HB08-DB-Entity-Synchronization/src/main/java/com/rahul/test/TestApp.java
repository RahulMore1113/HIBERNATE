package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {

		Session session = null;
		int id = 7;

		try {
			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, id);
			System.out.println("Before updation in the table :: " + student);

			if (session != null) {
				System.in.read();

				session.refresh(student);

				System.out.println("After updation in the table :: " + student);
			} else
				System.out.println("Record not available for the given ID :: " + id);

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
