package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class SelectQueryApp {
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Query query = session.createQuery("from com.rahul.model.InsurancePolicy");

			query.setFirstResult(0);
			query.setMaxResults(3);

			List<InsurancePolicy> list = query.list();

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
