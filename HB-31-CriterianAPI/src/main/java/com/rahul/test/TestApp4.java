package com.rahul.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

public class TestApp4
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
				
				ProjectionList list = Projections.projectionList();
				list.add(Projections.property("pname"));
				list.add(Projections.property("qty"));
				
				criteria.setProjection(list);
				
				Criterion cond1 = Restrictions.ge("price", 10000);
				Criterion cond2 = Restrictions.le("price", 60000);
				
				criteria.add(cond1);
				criteria.add(cond2);
				
				Order order = Order.asc("pname");
				
				criteria.addOrder(order);
				
				List<Object []> list2 = criteria.list();
				
				list2.forEach(row -> {
					for (Object obj : row)
						System.out.print(obj + "\t");
					System.out.println();
				});
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