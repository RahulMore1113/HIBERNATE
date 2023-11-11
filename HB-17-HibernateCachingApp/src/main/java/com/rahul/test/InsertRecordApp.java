package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Long id = null;

		try {
			session = HibernateUtil.getSession();
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				InsurancePolicy policy = new InsurancePolicy();
				policy.setCompany("HDFC");
				policy.setPolicyName("Jeevan Anand");
				policy.setPolicyType("yearly");
				policy.setTenure(5);

				id = (Long) session.save(policy);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved to database with ID :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object failed to save into database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
