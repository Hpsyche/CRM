package com.company.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.company.domain.Customer;
import com.company.service.CustomerService;
import com.company.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	// 用模型驱动接收表单参数
	private Customer customer = new Customer();
	// 用属性驱动接收需要查看的页面参数
	private Integer currentPage;
	// 用属性驱动接收每页显示的记录数
	private Integer pageSize;
	// 用属性驱动接收统计参数
	private String countMsg;

	// 依赖service
	private CustomerService<Customer> customerService;

	/*
	 * 处理客户列表的分页查询请求 接收前端传入的参数： 1、查询条件（cust_name）封装到Customer对象中，用模型驱动接收；
	 * 2、需要查看的页面（默认为第一页），用属性驱动接收； 3、每页显示的记录数，用属性驱动接收；
	 */
	public String list() throws Exception {
		// 将查询条件封装成DetachedCriteria对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		if (StringUtils.isNotBlank(customer.getCust_name())) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		// 调用service查询分页信息，返回PageBean
		PageBean<Customer> resultpPageBean = customerService.getPageBean(detachedCriteria, currentPage, pageSize);
		// 将PageBean保存到request域
		ActionContext.getContext().put("pageBean", resultpPageBean);
		// 转发到list.jsp
		return "list";
	}

	public String addOrUpdate() throws Exception {
		// 调用Service保存客户
		customerService.saveOrUpdate(customer);
		// 重定向到list
		return "toList";
	}
	
	public String delete() throws Exception {
		// 调用Service删除客户
		customerService.deleteById(customer.getCust_id());
		return null;
	}
	
	public String toEdit() throws Exception {
		// 根据id查询Customer对象
		Customer resultCustomer=customerService.getById(customer.getCust_id());
		// 保存到request域
		ActionContext.getContext().put("customer", resultCustomer);
		// 转发到修改页面
		return "edit";
	}
	
	public String getIndustryCount() throws Exception {
		
		// 调用service获取行业统计信息
		List<Object[]> resultList = customerService.getCount(countMsg);
		// 将列表保存到request域中
		ActionContext.getContext().put("industry", resultList);
		// 转发到统计页面
		return "industryList";
	}
	
	public String getSourceCount() throws Exception {
		
		// 调用service获取行业统计信息
		List<Object[]> resultList = customerService.getCount(countMsg);
		// 将列表保存到request域中
		ActionContext.getContext().put("source", resultList);
		// 转发到统计页面
		return "sourceList";
	}

	@Override
	public Customer getModel() {
		return customer;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setCustomerService(CustomerService<Customer> customerService) {
		this.customerService = customerService;
	}

	public String getCountMsg() {
		return countMsg;
	}

	public void setCountMsg(String countMsg) {
		this.countMsg = countMsg;
	}
	
}
