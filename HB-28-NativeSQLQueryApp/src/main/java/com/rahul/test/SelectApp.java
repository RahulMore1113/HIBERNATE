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

			NativeQuery query = session
					.createNativeQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");

			query.setParameter(1, 15);
			query.setParameter(2, 30);

			List<Object[]> policies = query.getResultList();

			System.out.println("PID\tPNAME\tPTYPE\tTENURE\tCOMPANY");

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
