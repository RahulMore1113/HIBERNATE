package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.rahul.util.HibernateUtil;

public class SelectApp4 {

	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<String> query = session
					.createNativeQuery("SELECT POLICYNAME FROM INSURANCEPOLICY WHERE TENURE>=:max AND TENURE<=:min");

			query.setParameter("max", 15);
			query.setParameter("min", 30);

			List<String> policies = query.getResultList();

			policies.forEach(System.out::println);
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
