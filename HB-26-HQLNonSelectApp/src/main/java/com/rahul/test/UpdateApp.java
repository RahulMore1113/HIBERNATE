package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rahul.util.HibernateUtil;

public class UpdateApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int count = 0;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session.createQuery(
					"UPDATE com.rahul.model.Product SET qty = qty + :newQty WHERE pname like :initialLatter");

			query.setParameter("newQty", 5);
			query.setParameter("initialLatter", "A%");

			count = query.executeUpdate();

			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("No of rows affected is :: " + count);
			} else {
				transaction.rollback();
				System.out.println("Failed to update");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
