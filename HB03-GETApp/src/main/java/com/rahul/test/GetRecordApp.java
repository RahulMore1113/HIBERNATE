package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class GetRecordApp {

	public static void main(String[] args) {

		Session session = null;
		int id = 1;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				Student student = session.get(Student.class, id);

				if (student != null)
					System.out.println(student);
				else
					System.out.println("Record not available for given id :: " + id);
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
