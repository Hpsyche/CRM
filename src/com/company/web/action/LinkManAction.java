package com.company.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.company.domain.LinkMan;
import com.company.service.LinkManService;
import com.company.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	// 模型驱动接收参数
	private LinkMan linkMan =new LinkMan();
	// 用属性驱动接收需要查看的页面参数
	private Integer currentPage;
	// 用属性驱动接收每页显示的记录数
	private Integer pageSize;
	// 依赖LinkManService
	private LinkManService linkManService;
	
	public String addOrUpdate() throws Exception {
		// 调用service保存
		linkManService.saveOrUpdate(linkMan);
		return null;
	}
	
	/*
	 * 处理联系人列表的分页查询请求 接收前端传入的参数： 1、查询条件（lkm_name）封装到LinkMan对象中，用模型驱动接收；
	 * 2、需要查看的页面（默认为第一页），用属性驱动接收； 3、每页显示的记录数，用属性驱动接收；
	 */
	public String list() throws Exception {
		// 将查询条件封装成DetachedCriteria对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			detachedCriteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		// 调用service查询分页信息，返回PageBean
		PageBean<LinkMan> resultpPageBean = linkManService.getPageBean(detachedCriteria, currentPage, pageSize);
		// 将PageBean保存到request域
		ActionContext.getContext().put("pageBean", resultpPageBean);
		// 转发到list.jsp
		return "list";
	}
	
	public String delete() throws Exception {
		// 调用Service删除联系人
		linkManService.deleteById(linkMan.getLkm_id());
		// 重定向到list
		return "toList";
	}
	
	public String toEdit() throws Exception {
		// 根据id获取联系人对象
		LinkMan resultLinkMan=linkManService.getById(linkMan.getLkm_id());
		// 保存到request域
		ActionContext.getContext().put("linkMan", resultLinkMan);
		// 转发到修改页面
		return "edit";
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
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
