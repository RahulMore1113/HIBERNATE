package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rahul.util.HibernateUtil;

public class DeleteApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int count = 0;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session.createQuery("DELETE FROM com.rahul.model.Product WHERE pid = :id");

			query.setParameter("id", 3);

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
				System.out.println("Failed to delete");
			}
		}

	}

}
