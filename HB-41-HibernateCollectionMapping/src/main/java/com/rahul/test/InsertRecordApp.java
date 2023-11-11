package com.rahul.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Employee;
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
				Employee employee = new Employee();
				employee.setEid(10);
				employee.setEname("Rahul");
				employee.setEaddress("Pune");

				employee.setFriendList(List.of("Prashant", "Alankar", "Anish"));
				employee.setPhones(Set.of(112233L, 223344L, 334455L));
				employee.setBankAccount(Map.of("SBI", 1234L, "HDFC", 1212L, "KOTAK", 8888L));

				session.save(employee);

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
