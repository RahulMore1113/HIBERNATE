package com.rahul.service;

import java.util.ArrayList;
import java.util.List;

import com.rahul.dao.InsurancePolicyDao;
import com.rahul.dao.InsurancePolicyDaoImpl;
import com.rahul.dto.InsurancePolicyDTO;
import com.rahul.model.InsurancePolicy;

public class InsurancePolicyServiceImpl implements InsurancePolicyService 
{
	InsurancePolicyDao dao;
	
	public InsurancePolicyServiceImpl() 
	{
		dao = new InsurancePolicyDaoImpl();
	}
	
	@Override
	public long fetchPageCount(int pageSize) 
	{
		Long totalRecords = dao.getTotalRecordsCount();
		Long pagesCount = totalRecords / pageSize;
		
		if (totalRecords % pageSize != 0)
			pagesCount++;
		
		return pagesCount;
	}

	@Override
	public List<InsurancePolicyDTO> fetchPageData(int pageSize, int pageNo) 
	{
		int startPos = 0;
		startPos = (pageNo * pageSize) - pageSize;

		List<InsurancePolicyDTO> list = new ArrayList<>();
		List<InsurancePolicy> entities = dao.getPageData(pageSize, startPos);
		
		entities.forEach(entity -> {
			InsurancePolicyDTO dto = new InsurancePolicyDTO();
			dto.setPolicyId(entity.getPolicyId());
			dto.setPolicyName(entity.getPolicyName());
			dto.setPolicyType(entity.getPolicyType());
			dto.setCompany(entity.getCompany());
			dto.setTenure(entity.getTenure());
			list.add(dto);
		});
		
		return list;
	}

}
