package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class ClientApp2 
{
	public static void main(String[] args) 
	{
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		InsurancePolicy policy = null;
		
		try
		{
			session = HibernateUtil.getSession();
			
			if (session != null)
				transaction = session.beginTransaction();
			
			if (transaction != null)
			{
				policy = session.get(InsurancePolicy.class, 1);
				
				System.out.println(policy);
				
				policy.setTenure(15);
				
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
				System.out.println("Object updated to databse with version count :: " + policy.getCount());
			}
			else
			{
				transaction.rollback();
				System.out.println("Object failed to save in database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
