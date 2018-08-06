package com.company.service;

import org.hibernate.criterion.DetachedCriteria;

import com.company.domain.SaleVisit;
import com.company.utils.PageBean;

public interface SaleVisitService {

	void saveOrUpdate(SaleVisit saleVisit);

	PageBean<SaleVisit> getPageBean(DetachedCriteria detachedCriteria,
			Integer currentPage, Integer pageSize);

	SaleVisit getById(String visit_id);

	void deleteById(String visit_id);

}
