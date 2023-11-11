package com.rahul.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

public class TestApp2
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
				
				Criterion cond1 = Restrictions.ge("price", 22000);
				Criterion cond2 = Restrictions.le("price", 60000);
				
				criteria.add(cond1);
				criteria.add(cond2);
				
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