package com.rahul.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.model.Student;

public class PureJavaApp 
{

	public static void main(String[] args) 
	{
		
		Session session = null;
		SessionFactory factory = null;
		int id = 1;
		
		try
		{
			Configuration configuration = new Configuration();
			
			configuration.setProperty("hibernate.connection.driver_class" , "com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url" , "jdbc:mysql://localhost:3306/hiber");
			configuration.setProperty("hibernate.connection.username" , "root");
			configuration.setProperty("hibernate.connection.password" , "Rahul@1113");
			
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			configuration.setProperty("hibernate.show_sql", "true");
			configuration.setProperty("hibernate.format_sql", "true");
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			
			configuration.addAnnotatedClass(Student.class);
			
			factory = configuration.buildSessionFactory();
			
			session = factory.openSession();
			
			Student student = session.get(Student.class, id);
			
			if (student != null)
				System.out.println("Before updation in the table " + student);
			else
				System.out.println("Record not found for the given ID :: " + id);
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
