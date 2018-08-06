package com.company.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.company.domain.Customer;
import com.company.utils.PageBean;

public interface CustomerService <T>{

	// 查询分页信息业务方法
	PageBean<T> getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	// 保存或更新客户
	void saveOrUpdate(Customer customer);
	
	// 根据id查询客户
	Customer getById(Long cust_id);
	
	// 获取行业统计信息
	List<Object[]> getCount(String query);

	void deleteById(Long cust_id);

}
