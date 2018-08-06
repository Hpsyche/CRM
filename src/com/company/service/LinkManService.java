package com.company.service;

import org.hibernate.criterion.DetachedCriteria;

import com.company.domain.LinkMan;
import com.company.utils.PageBean;

public interface LinkManService {

	void saveOrUpdate(LinkMan linkMan);

	PageBean<LinkMan> getPageBean(DetachedCriteria detachedCriteria,
			Integer currentPage, Integer pageSize);

	LinkMan getById(Long lkm_id);

	void deleteById(Long lkm_id);

}
