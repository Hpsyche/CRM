package com.company.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.company.domain.SaleVisit;
import com.company.domain.User;
import com.company.service.SaleVisitService;
import com.company.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	
	// 模型驱动接收参数
	private SaleVisit saleVisit=new SaleVisit();
	// 依赖SaleVisitService
	private SaleVisitService saleVisitService;
	// 用属性驱动接收需要查看的页面参数
	private Integer currentPage;
	// 用属性驱动接收每页显示的记录数
	private Integer pageSize;
	
	public String addOrUpdate() throws Exception {
		// 获取登陆用户并添加到saleVisit
		User loginUser = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(loginUser);
		// 调用service保存saleVisit
		saleVisitService.saveOrUpdate(saleVisit);
		// 重定向到客户拜访列表
		return "toList";
	}
	
	public String list() throws Exception {
		// 将查询条件封装成DetachedCriteria对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getCustomer()!=null&&saleVisit.getCustomer().getCust_id()!=null){
			detachedCriteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		// 调用service查询分页信息，返回PageBean
		PageBean<SaleVisit> resultpPageBean = saleVisitService.getPageBean(detachedCriteria, currentPage, pageSize);
		// 将PageBean保存到request域
		ActionContext.getContext().put("pageBean", resultpPageBean);
		// 转发到list.jsp
		return "list";
	}
	
	public String delete() throws Exception {
		// 调用Service删除客户
		saleVisitService.deleteById(saleVisit.getVisit_id());
		return null;
	}
	
	public String toEdit() throws Exception {
		// 根据id查询Customer对象
		SaleVisit resultSaleVisit=saleVisitService.getById(saleVisit.getVisit_id());
		// 保存到request域
		ActionContext.getContext().put("saleVisit", resultSaleVisit);
		// 转发到修改页面
		return "edit";
	}

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
