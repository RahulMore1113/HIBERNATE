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
		
		try
		{
			session = HibernateUtil.getSession();
			
			if (session != null)
				transaction = session.beginTransaction();
			
			if (transaction != null)
			{
				Student student = new Student();
				student.setSid(7);
				student.setSname("MS");
				student.setSage(22);
				student.setSaddress("Pune");
				
				session.save(student);
				
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
				System.out.println("Object is SaveorUpdated");
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
