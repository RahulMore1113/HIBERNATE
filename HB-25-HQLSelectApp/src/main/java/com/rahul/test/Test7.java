package com.rahul.test;

import java.util.Optional;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

public class Test7 {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Query<Product> query = session.createQuery("FROM com.rahul.model.Product WHERE pid = :id");

			System.out.println("Enter the id of the product to be fetch :: ");
			int id = new Scanner(System.in).nextInt();

			query.setParameter("id", id);

			Optional<Product> optional = query.uniqueResultOptional();

			if (optional.isPresent()) {
				Product product = optional.get();
				System.out.println(product);
			} else
				System.out.println("Record not available for given id :: " + id);
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
