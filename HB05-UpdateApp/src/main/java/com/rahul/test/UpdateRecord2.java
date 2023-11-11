package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class UpdateRecord2 {

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
					System.out.println("Old Student Record before updation");
					System.out.println(student);
					System.out.println();
					student.setSname("More");
					student.setSaddress("Pune");
					System.out.println("New Student Record after updation");
					System.out.println(student);

					session.update(student);

					flag = true;
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Student odbject is Updated");
			} else {
				transaction.rollback();
				System.out.println("Student Object failed to update");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
