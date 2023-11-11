package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.ProgrammerProjId;
import com.rahul.model.ProgrammerProjInfo;
import com.rahul.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				ProgrammerProjId pid = new ProgrammerProjId();
				pid.setPid(100);
				pid.setProjId(501);

				ProgrammerProjInfo info = session.get(ProgrammerProjInfo.class, pid);

				if (info != null)
					System.out.println("Details of project :: \n" + info);
				else
					System.out.println("Details not available for given id :: " + pid);
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
