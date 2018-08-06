package com.company.service.impl;

import java.util.List;

import com.company.dao.BaseDictDao;
import com.company.domain.BaseDict;
import com.company.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {
	
	private BaseDictDao baseDictDao;
	
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		
		// 调用BaseDictDao查询字典list
		List<BaseDict> list=baseDictDao.getListByTypeCode(dict_type_code);
		
		return list;
	}

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	
}
