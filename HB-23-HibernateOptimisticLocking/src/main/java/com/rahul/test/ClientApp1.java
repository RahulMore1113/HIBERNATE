package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class ClientApp1 
{
	public static void main(String[] args) 
	{
		Session session = null;
		Transaction transaction = null;
		InsurancePolicy policy = null;
		boolean flag = false;
		
		try
		{
			session = HibernateUtil.getSession();
			
			if (session != null)
				transaction = session.beginTransaction();
			
			if (transaction != null)
			{
				policy = session.get(InsurancePolicy.class, 1);
				System.out.println(policy);
				
				Thread.sleep(30000);
				
				policy.setTenure(14);
				
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
				System.out.println("Object updated to database with Version Count :: " + policy.getCount());
				System.out.println("New updated policy :: " + policy);
			}
			else
			{
				transaction.rollback();
				System.out.println("Object failed to update in database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
