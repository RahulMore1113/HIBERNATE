package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class UpdateRecord3 {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, 2);

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				if (student != null) {
					System.out.println("Record before updation");
					System.out.println(student);
					System.out.println();
					student.setSage(22);
					System.out.println("Record after updation");
					System.out.println(student);

					flag = true;
				} else
					System.out.println("Record not available for updation");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Student Object updated");
			} else {
				transaction.rollback();
				System.out.println("Student object failed to update");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
