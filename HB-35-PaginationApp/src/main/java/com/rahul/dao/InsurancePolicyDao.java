package com.rahul.dao;

import java.util.List;

import com.rahul.model.InsurancePolicy;

public interface InsurancePolicyDao {
	List<InsurancePolicy> getPageData(int pageSize, int startPos);

	Long getTotalRecordsCount();
}
