package com.rahul.test;

import com.rahul.dao.ITransferDao;
import com.rahul.dao.TransferDaoImpl;
import com.rahul.util.HibernateUtil;
import com.rahul.util.HibernateUtil2;

public class InteractionWithMultipleDB 
{

	public static void main(String[] args) 
	{
		
		ITransferDao dao = new TransferDaoImpl();
		String result = dao.transferProjectById(1);
		System.out.println(result);
		
		HibernateUtil.closeSessionFactory();
		HibernateUtil2.closeSessionFactory();
		
	}

}
