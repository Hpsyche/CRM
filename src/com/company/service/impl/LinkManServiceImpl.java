package com.company.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.company.dao.LinkManDao;
import com.company.domain.LinkMan;
import com.company.service.LinkManService;
import com.company.utils.PageBean;

public class LinkManServiceImpl implements LinkManService {
	private LinkManDao linkManDao;
	@Override
	public void saveOrUpdate(LinkMan linkMan) {
		// 调用dao保存联系人
		linkManDao.saveOrUpdate(linkMan);
	}
	
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> getPageBean(DetachedCriteria detachedCriteria,
			Integer currentPage, Integer pageSize) {
		// 查询总页记录数totalCount
		Integer totalCount = linkManDao.getTotalCount(detachedCriteria);
		// 创建PageBean对象
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>(totalCount, currentPage, pageSize);
		// 查询客户列表对象
		List<LinkMan> list = linkManDao.getPageList(detachedCriteria, pageBean.getStartIndex(), pageBean.getPageSize());
		// 组装PageBean对象
		pageBean.setList(list);
		// 返回PageBean对象
		return pageBean;
	}

	@Override
	public LinkMan getById(Long lkm_id) {
		return linkManDao.getById(lkm_id);
	}

	@Override
	public void deleteById(Long lkm_id) {
		linkManDao.delete(lkm_id);
	}

}
