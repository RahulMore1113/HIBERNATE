package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Student;
import com.rahul.util.HibernateUtil;

public class InsertApp 
{

	public static void main(String[] args) 
	{
		
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		try
		{
			session = HibernateUtil.getSession();
			
			if (session != null)
				transaction = session.beginTransaction();
			
			if (transaction != null)
			{
				Student student = new Student();
				student.setSname("Harald");
				student.setSage(45);
				student.setSaddress("Mumbai");
				
				id = (Integer) session.save(student);
				
				flag = true;
			}
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (flag)
			{
				transaction.commit();
				System.out.println("Object is SaveorUpdated with id :: " + id);
			}
			else
			{
				transaction.rollback();
				System.out.println("Object not SaveorUpdated");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
