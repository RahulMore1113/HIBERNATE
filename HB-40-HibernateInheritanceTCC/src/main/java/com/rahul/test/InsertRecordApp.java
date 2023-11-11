package com.rahul.test;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.CardPayment;
import com.rahul.model.ChequePayment;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp {
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				CardPayment cardPayment = new CardPayment();
				cardPayment.setPid(1234);
				cardPayment.setAmount(8000.5f);

				cardPayment.setCardNo(45678L);
				cardPayment.setCardType("Debit");
				cardPayment.setPaymentGateway("visa");

				ChequePayment chequePayment = new ChequePayment();
				chequePayment.setPid(1235);
				chequePayment.setAmount(4500.56f);

				chequePayment.setChequeNo(546345L);
				chequePayment.setChequeType("self");
				chequePayment.setExpriyDate(LocalDate.of(23, 4, 27));

				session.save(cardPayment);
				session.save(chequePayment);

				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved to database");
			} else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
