package com.rahul.test;

import com.rahul.dao.InsurancePolicyDao;
import com.rahul.dao.InsurancePolicyDaoImpl;

public class InsertRecordApp {

	public static void main(String[] args) {

		InsurancePolicyDao dao = new InsurancePolicyDaoImpl();
		String result = dao.transferPolicies(10);
		System.out.println(result);

	}

}
