package com.rahul.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.rahul.model.BankAccount;
import com.rahul.util.HibernateUtil;

public class SelectAppNativeSQL {
	public static void main(String[] args) {
		Session session = null;

		try {
			Filter filter = session.enableFilter("FILTER_BANK_ACCOUNT_STATUS");
			filter.setParameter("accType1", "blocked");
			filter.setParameter("accType2", "closed");

			NativeQuery<BankAccount> nQuery = session
					.createNativeQuery("select * from bankaccount where balance>=:amt");
			nQuery.setParameter("amt", 25000.0f);
			nQuery.addEntity(BankAccount.class);

			List<BankAccount> account = nQuery.list();
			account.forEach(System.out::println);

			System.out.println();

			session.disableFilter("FILTER_BANK_ACCOUNT_STATUS");
			NativeQuery<BankAccount> nQuery1 = session
					.createNativeQuery("select * from bankaccount where balance>=:amt");
			nQuery1.setParameter("amt", 25000.0f);
			nQuery1.addEntity(BankAccount.class);

			List<BankAccount> account1 = nQuery1.list();
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
