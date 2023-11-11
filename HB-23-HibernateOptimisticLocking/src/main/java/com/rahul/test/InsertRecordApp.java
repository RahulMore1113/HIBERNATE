package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.InsurancePolicy;
import com.rahul.util.HibernateUtil;

public class InsertRecordApp 
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
				InsurancePolicy policy = new InsurancePolicy();
				policy.setPname("Kotak");
				policy.setType("Salary");
				policy.setTenure(11);
				
				id = (Integer) session.save(policy);
				
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
				System.out.println("Object save to database with ID :: " + id);
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
