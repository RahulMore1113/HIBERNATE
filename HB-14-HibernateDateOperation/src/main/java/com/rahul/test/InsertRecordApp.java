package com.rahul.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.PersonInfo;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				PersonInfo info = new PersonInfo();
				info.setPname("Rahul");
				info.setDob(LocalDate.of(2000, 02, 07));
				info.setDoj(LocalTime.of(9, 30));
				info.setDom(LocalDateTime.of(LocalDate.of(2028, 02, 07), LocalTime.of(12, 45)));

				id = (Integer) session.save(info);

				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved with the id :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object not saved.");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
