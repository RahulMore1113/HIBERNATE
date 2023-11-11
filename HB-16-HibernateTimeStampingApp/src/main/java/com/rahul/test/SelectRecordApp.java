package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.BankAccount;
import com.rahul.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) {

		Session session = null;
		int id = 1;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				BankAccount account = session.get(BankAccount.class, id);

				if (account != null)
					System.out.println(account);
				else
					System.out.println("Record not available for given ID :: " + id);
			}
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
