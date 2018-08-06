package com.company.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.company.dao.CustomerDao;
import com.company.domain.Customer;
import com.company.service.CustomerService;
import com.company.utils.PageBean;

public class CustomerServiceImpl implements CustomerService<Customer> {

	// 依赖Dao
	private CustomerDao customerDao;

	@Override
	public PageBean<Customer> getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// 查询总页记录数totalCount
		Integer totalCount = customerDao.getTotalCount(detachedCriteria);
		// 创建PageBean对象
		PageBean<Customer> pageBean = new PageBean<Customer>(totalCount, currentPage, pageSize);
		// 查询客户列表对象
		List<Customer> list = customerDao.getPageList(detachedCriteria, pageBean.getStartIndex(), pageBean.getPageSize());
		// 组装PageBean对象
		pageBean.setList(list);
		// 返回PageBean对象
		return pageBean;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		/* 维护表关系（Customer与BaseDict的多对一关系）
		 * 由于Customer的BaseDict类型的属性已经由struts在接收参数时创建初始化，具有与base_dict表对应的id
		 * 因此BaseDict类型的属性值属于游离对象，足以维护关系
		 */
		// 调用customerDao保存客户
		customerDao.saveOrUpdate(customer);
	}

	@Override
	public Customer getById(Long cust_id) {
		return customerDao.getById(cust_id);
	}
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Object[]> getCount(String query) {
		query="cust_"+query;
		return customerDao.getCount(query);
	}

	@Override
	public void deleteById(Long cust_id) {
		customerDao.delete(cust_id);
	}

}
