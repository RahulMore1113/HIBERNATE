package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.rahul.model.StudentInfo;
import com.rahul.util.HibernateUtil;

public class SelectRecordApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();
			@SuppressWarnings("deprecation")
			Query<StudentInfo> query = session
					.createQuery("from com.rahul.model.StudentInfo where address.cityName=:city");
			query.setParameter("city", "Bengaluru");

			List<StudentInfo> student = query.list();
			student.forEach(System.out::println);
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
