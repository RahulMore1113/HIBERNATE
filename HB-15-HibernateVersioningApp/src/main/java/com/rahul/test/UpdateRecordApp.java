package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.MobileCustomer;
import com.rahul.util.HibernateUtil;

public class UpdateRecordApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		MobileCustomer customer = null;

		try {
			session = HibernateUtil.getSession();
			customer = session.get(MobileCustomer.class, 1);
			System.out.println("Loading Object :: " + customer);

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				if (customer != null) {
					customer.setCallerTune("AyeVatan");

					session.update(customer);

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
				System.out
						.println("Object updated to database with '" + customer.getVersionCount() + "' count / times");
				System.out.println("Record after modification :: " + customer);
			} else {
				transaction.rollback();
				System.out.println("Object updation failed");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
