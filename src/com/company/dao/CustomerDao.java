package com.company.dao;

import java.util.List;

import com.company.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{
	
	// 客户统计信息
	List<Object[]> getCount(final String query);
	
}
