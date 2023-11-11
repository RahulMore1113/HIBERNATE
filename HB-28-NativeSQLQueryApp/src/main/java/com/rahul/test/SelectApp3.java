package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.rahul.util.HibernateUtil;

public class SelectApp3 {

	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<Object[]> query = session.createNativeQuery(
					"SELECT POLICYID,POLICYNAME,COMPANY FROM INSURANCEPOLICY WHERE TENURE>=:max AND TENURE<=:min");

			query.setParameter("max", 15);
			query.setParameter("min", 30);

			query.addScalar("POLICYID", StandardBasicTypes.INTEGER);
			query.addScalar("POLICYNAME", StandardBasicTypes.STRING);
			query.addScalar("COMPANY", StandardBasicTypes.STRING);

			List<Object[]> policies = query.getResultList();

			System.out.println("PID\tPNAME\tCOMPANY");

			policies.forEach(row -> {
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
