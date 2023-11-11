package com.rahul.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

public class TestApp
{
	public static void main (String []args)
	{
		Session session = null;
		
		try
		{
			session = HibernateUtil.getSession();
			
			if (session != null)
			{
				Criteria criteria = session.createCriteria(Product.class);
				
				List<Product> list = criteria.list();
				
				list.forEach(System.out::println);
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
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}