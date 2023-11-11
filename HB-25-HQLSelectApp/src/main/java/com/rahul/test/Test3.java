package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.util.HibernateUtil;

public class Test3 {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Query<Object[]> query = session
					.createQuery("SELECT price, qty FROM com.rahul.model.Product WHERE pname IN (:prod1, :prod2)");

			query.setParameter("prod1", "Laptop");
			query.setParameter("prod2", "Apple");

			List<Object[]> list = query.list();

			System.out.println("PRICE\tQTY");

			list.forEach(row -> {
				for (Object obj : row)
					System.out.print(obj + "\t");
				System.out.println();
			});
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
