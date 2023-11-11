package com.rahul.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class StudentDaoImpl implements IStudentDao {

	private Session session = HibernateUtil.getSession();
	private Transaction transaction = null;

	@Override
	public String insert(String sname, Integer sage, String saddress) {

		boolean flag = false;
		String status = "";

		try {

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				Student student = new Student();
				student.setSname(sname);
				student.setSage(sage);
				student.setSaddress(saddress);

				session.save(student);

				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failed";
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		return status;
	}

	@Override
	public Student read(Integer sid) {

		Student student = null;
		try {
			student = session.get(Student.class, sid);

			if (student != null)
				return student;

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		return student;
	}

	@Override
	public String update(Student student) {
		boolean flag = false;
		String status = "";

		try {
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				if (student != null) {
					Student student2 = session.get(Student.class, student.getSid());
					if (student != null) {
						student2.setSname(student.getSname());
						student2.setSage(student.getSage());
						student2.setSaddress(student.getSaddress());

						session.update(student2);

						flag = true;
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failed";
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		return status;
	}

	@Override
	public String delete(Integer sid) {

		boolean flag = false;
		String status = "";

		try {
			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				Student student = session.get(Student.class, sid);

				if (student != null) {
					session.delete(student);

					flag = true;
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failed";
			}
		}
		return status;
	}

}
