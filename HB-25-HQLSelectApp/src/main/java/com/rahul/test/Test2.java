package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

public class Test2 {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Query<Product> query = session.createQuery("FROM com.rahul.model.Product WHERE pname IN (:prod1, :prod2)");

			query.setParameter("prod1", "Apple");
			query.setParameter("prod2", "Laptop");

			List<Product> list = query.list();

			list.forEach(System.out::println);
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
