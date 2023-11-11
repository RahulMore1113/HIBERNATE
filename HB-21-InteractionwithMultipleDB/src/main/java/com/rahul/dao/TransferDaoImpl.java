package com.rahul.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;
import com.rahul.util.HibernateUtil2;

public class TransferDaoImpl implements ITransferDao {

	@SuppressWarnings("finally")
	public String transferProjectById(Integer id) {
		Session session1 = HibernateUtil.getSession();
		Session session2 = HibernateUtil2.getSession();

		Transaction transaction = null;

		boolean flag = false;
		Integer idValue = null;

		Product product = session1.get(Product.class, id);

		if (product == null)
			return "Record not available for copying...";
		else {
			try {
				transaction = session2.beginTransaction();
				idValue = (Integer) session2.save(product);
				flag = true;
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (flag) {
					transaction.commit();
					return "Data copied from hiber to more with id :: " + idValue;
				} else {
					transaction.rollback();
					return "Data not copied from hiber to morewith id :: " + idValue;
				}
			}
		}
	}

}
