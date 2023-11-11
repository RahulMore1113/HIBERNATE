package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.model.Student;

public class PropertiesFileApp 
{

	public static void main(String[] args) 
	{
		
		Session session = null;
		SessionFactory factory = null;
		int id = 1;
		
		try
		{
			factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
			
			session = factory.openSession();
			
			Student student = session.get(Student.class, id);
			
			if (student != null)
				System.out.println("Student Object Data :: " + student);
			else
				System.out.println("Student Object data not found for given ID :: " + id);
			
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
			session.close();
			factory.close();
		}
		
	}

}
