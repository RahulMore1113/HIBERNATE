package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class LoadRecordApp {

	public static void main(String[] args) {

		Session session = null;
		int id = 1;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				Student student = session.load(Student.class, id);

				if (student != null) {
					System.out.println("Student ID :: " + student.getSid());
					System.out.println("Student NAME :: " + student.getSname());
					System.err.println("Student AGE :: " + student.getSage());
					System.out.println("Student ADDRESS :: " + student.getSaddress());
				} else
					System.out.println("Student record not available for given id :: " + id);
			}
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
