package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.rahul.util.HibernateUtil;

public class SelectApp {

	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<Object[]> query = session.getNamedNativeQuery("GET_ALL_POLICIES_TYPE");

			query.setParameter(1, "quarterly");

			List<Object[]> policies = query.getResultList();

			System.out.println("PID\tPNAME\tPTYPE");

			policies.forEach(row -> {
				for (Object policy : row)
					System.out.print(policy + "\t");
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
