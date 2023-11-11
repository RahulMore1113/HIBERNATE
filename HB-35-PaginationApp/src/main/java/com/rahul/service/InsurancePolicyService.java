package com.rahul.service;

import java.util.List;

import com.rahul.dto.InsurancePolicyDTO;

public interface InsurancePolicyService {
	long fetchPageCount(int pageSize);

	List<InsurancePolicyDTO> fetchPageData(int pageSize, int pageNo);
}
