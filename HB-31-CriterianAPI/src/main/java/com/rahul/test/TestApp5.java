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

public class TestApp5
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
				
				Criterion cond1 = Restrictions.between("price", 65000, 10000);
				Criterion cond2 = Restrictions.in("pname", "fossil", "tissot", "armani");
				Criterion cond3 = Restrictions.ilike("pname", "f%");

				Criterion finalCondition = Restrictions.or(Restrictions.and(cond1, cond2), cond3);

				criteria.add(finalCondition);

				Order order = Order.asc("pname");

				
				criteria.addOrder(order);

				List<Object[]> products = criteria.list();
				System.out.println("PNAME\tQTY");
				products.forEach(row -> {
					for (Object object : row) {
						System.out.print(object + "\t");
					}
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