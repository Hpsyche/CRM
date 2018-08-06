package com.company.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.company.dao.SaleVisitDao;
import com.company.domain.LinkMan;
import com.company.domain.SaleVisit;
import com.company.service.SaleVisitService;
import com.company.utils.PageBean;

public class SaleVisitServiceImpl implements SaleVisitService {
	
	// 依赖dao
	private SaleVisitDao saleVisitDao;
	
	@Override
	public void saveOrUpdate(SaleVisit saleVisit) {
		// 调用dao保存用户
		saleVisitDao.saveOrUpdate(saleVisit);
	}

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public PageBean<SaleVisit> getPageBean(DetachedCriteria detachedCriteria,
			Integer currentPage, Integer pageSize) {
		// 查询总页记录数totalCount
		Integer totalCount = saleVisitDao.getTotalCount(detachedCriteria);
		// 创建PageBean对象
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>(totalCount, currentPage, pageSize);
		// 查询客户列表对象
		List<SaleVisit> list = saleVisitDao.getPageList(detachedCriteria, pageBean.getStartIndex(), pageBean.getPageSize());
		// 组装PageBean对象
		pageBean.setList(list);
		// 返回PageBean对象
		return pageBean;
	}

	@Override
	public SaleVisit getById(String visit_id) {
		return saleVisitDao.getById(visit_id);
	}

	@Override
	public void deleteById(String visit_id) {
		saleVisitDao.delete(visit_id);
	}
	
}
