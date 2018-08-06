package com.company.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.company.dao.BaseDictDao;
import com.company.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements
		BaseDictDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {

		// 创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BaseDict.class);
		detachedCriteria.add(Restrictions.eq("dict_type_code", dict_type_code));
		// 查询符合条件的字典列表
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate()
				.findByCriteria(detachedCriteria);
		return list;
	}

}
