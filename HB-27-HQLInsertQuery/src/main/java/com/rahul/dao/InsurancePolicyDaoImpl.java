package com.rahul.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rahul.util.HibernateUtil;

public class InsurancePolicyDaoImpl implements InsurancePolicyDao {

	private static final String HQL_TRANSFER_POLICIES = "INSERT INTO com.rahul.model.PremiumInsurancePolicy(policyId, policyName, policyType, company, tenure) SELECT i.policyId, i.policyName, i.policyType, i.company, i.tenure FROM com.rahul.model.InsurancePolicy as i WHERE i.tenure >= :min";

	@Override
	public String transferPolicies(int minTenure) {
		Session session = null;
		Transaction transaction = null;
		Query query = null;
		int rowAffected = 0;
		boolean flag = false;
		String msg = "";

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			query = session.createQuery(HQL_TRANSFER_POLICIES);
			query.setParameter("min", minTenure);

			rowAffected = query.executeUpdate();

			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				msg = "No of rows transferred are :: " + rowAffected;
			} else {
				transaction.rollback();
				msg = "No of rows transferred are :: " + rowAffected;
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		return msg;
	}

}
