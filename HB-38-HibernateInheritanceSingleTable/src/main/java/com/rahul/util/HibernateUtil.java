package com.rahul.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.model.CardPayment;
import com.rahul.model.ChequePayment;
import com.rahul.model.Payment;

public class HibernateUtil {

	private static Session session = null;
	private static SessionFactory factory = null;

	private HibernateUtil() {

	}

	static {
		factory = new Configuration().configure()
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(CardPayment.class)
				.addAnnotatedClass(ChequePayment.class)
				.buildSessionFactory();
	}

	public static Session getSession() {
		if (session == null)
			session = factory.openSession();

		return session;
	}

	public static void closeSession(Session session) {
		if (session != null)
			session.close();
	}

	public static void closeSessionFactory() {
		if (factory != null)
			factory.close();
	}

}
