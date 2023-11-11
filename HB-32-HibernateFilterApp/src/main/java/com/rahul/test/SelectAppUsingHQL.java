package com.rahul.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.model.BankAccount;
import com.rahul.util.HibernateUtil;

public class SelectAppUsingHQL {
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Filter filter = session.enableFilter("FILTER_BANK_ACCOUNT_STATUS");
			filter.setParameter("accType1", "blocked");
			filter.setParameter("accType2", "closed");

			Query<BankAccount> query = session.createQuery("from com.rahul.BankAccount where balance >=:amt");
			query.setParameter("amt", 25000.0f);

			List<BankAccount> account = query.list();
			account.forEach(System.out::println);

			System.out.println();

			session.disableFilter("FILTER_BANK_ACCOUNT_STATUS");
			Query<BankAccount> query1 = session.createQuery("from in.ineuron.model.BankAccount where balance>=:amt");
			query1.setParameter("amt", 25000.0f);

			List<BankAccount> account1 = query1.list();
			account1.forEach(System.out::println);

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
