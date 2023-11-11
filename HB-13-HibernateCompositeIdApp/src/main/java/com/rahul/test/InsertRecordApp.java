package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.ProgrammerProjId;
import com.rahul.model.ProgrammerProjInfo;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		ProgrammerProjId pk = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				ProgrammerProjId pid = new ProgrammerProjId();
				pid.setPid(101);
				pid.setProjId(501);

				ProgrammerProjInfo pinfo = new ProgrammerProjInfo();
				pinfo.setId(pid);
				pinfo.setDeptName("SPRING");
				pinfo.setDeptNo(2);
				pinfo.setPname("MORE");

				pk = (ProgrammerProjId) session.save(pinfo);

				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object Inserted with the id :: " + pk);
			} else {
				transaction.rollback();
				System.out.println("Object insertion failed ");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
