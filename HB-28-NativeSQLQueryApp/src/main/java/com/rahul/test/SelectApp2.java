package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class SelectApp2 {

	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<InsurancePolicy> query = session
					.createNativeQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=:max AND TENURE<=:min");

			query.setParameter("max", 15);
			query.setParameter("min", 30);

			query.addEntity(InsurancePolicy.class);

			List<InsurancePolicy> policies = query.getResultList();

			System.out.println("PID\tPNAME\tPTYPE\tTENURE\tCOMPANY");

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
