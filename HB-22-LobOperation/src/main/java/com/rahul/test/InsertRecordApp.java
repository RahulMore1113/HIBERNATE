package com.rahul.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.JobSeeker;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		byte[] photo = null;
		char[] resume = null;
		File f = null;

		// logic for copying the image data to byte[]
		try (FileInputStream fis = new FileInputStream("P:\\A\\rahul.jpg")) {
			photo = new byte[fis.available()];
			fis.read(photo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// logic for copying the resume data to character array array
		try {
			f = new File("P:\\A\\rs.txt");
			try (FileReader fr = new FileReader(f)) {
				resume = new char[(int) f.length()];
				fr.read(resume);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				JobSeeker js = new JobSeeker();
				js.setJsName("Rahul");
				js.setJsAddress("Pune");
				js.setPhoto(photo);
				js.setResume(resume);

				id = (Integer) session.save(js);

				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object save in database with id :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object failed to save in database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
