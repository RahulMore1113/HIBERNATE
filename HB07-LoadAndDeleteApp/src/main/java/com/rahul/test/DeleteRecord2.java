package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class DeleteRecord2 {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int id = 2;

		try {
			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, id);

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				if (student != null) {
					session.delete(student);

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
				System.out.println("Student Object deleted");
			} else {
				transaction.rollback();
				System.out.println("Student Object not deleted");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
