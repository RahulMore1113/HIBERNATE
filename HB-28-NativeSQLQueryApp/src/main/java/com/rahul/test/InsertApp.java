package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.rahul.util.HibernateUtil;

public class InsertApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int rowAffected = 0;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				NativeQuery nativeQuery = session.createNativeQuery(
						"insert into insurancepolicy(policyName,policyType,tenure,company)values(?,?,?,?)");

				nativeQuery.setParameter(1, "Prudential");
				nativeQuery.setParameter(2, "quarterly");
				nativeQuery.setParameter(3, 24);
				nativeQuery.setParameter(4, "ICICI");

				rowAffected = nativeQuery.executeUpdate();

				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record inserted into database with id :: " + rowAffected);
			} else {
				transaction.rollback();
				System.out.println("Record failed to save in database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
