package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.BankAccount;
import com.rahul.util.HibernateUtil;

public class UpdateRecordApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		BankAccount account = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				account = session.get(BankAccount.class, 1);
				System.out.println("Loading the  Object :: " + account);

				transaction = session.beginTransaction();
				account.setBalance(account.getBalance() + 2000);
				session.update(account);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated to the database");
				System.out.println("Record after modification :: " + account);
			} else {
				transaction.rollback();
				System.out.println("Object updation failed");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
