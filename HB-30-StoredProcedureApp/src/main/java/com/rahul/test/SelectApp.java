package com.rahul.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;

import com.rahul.model.Product;
import com.rahul.util.HibernateUtil;

import jakarta.persistence.ParameterMode;

/**
 * CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GET_PRODUCT_BY_NAME`(IN name1
 * VARCHAR(20), IN name2 VARCHAR(20)) BEGIN SELECT pid,pname,price,qty FROM
 * products WHERE pname IN (name1,name2); END$$
 * 
 * DELIMITER ;
 *
 */
public class SelectApp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();
			String product1 = "fossil";
			String product2 = "tissot";

			// Creating a Procedure call object
			ProcedureCall procedureCall = session.createStoredProcedureCall("P_GET_PRODUCT_BY_NAME", Product.class);

			// Binding input parameter value for Procedure call object
			procedureCall.registerParameter(1, String.class, ParameterMode.IN);
			procedureCall.registerParameter(2, String.class, ParameterMode.IN);

			// Executing the store procedure to get the result
			List<Product> products = procedureCall.getResultList();

			// Processing the result
			products.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
