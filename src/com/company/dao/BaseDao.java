package com.company.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	// 保存或更新
	void saveOrUpdate(T t);
	// 增
	void save(T t);
	// 根据对象删除
	void delete(T t);
	// 根据id删除
	void delete(Serializable id);
	// 改
	void update(T t);
	// 根据id查询
	T getById(Serializable id);
	// 根据离线查询对象查寻符合条件的总记录数
	Integer getTotalCount(DetachedCriteria detachedCriteria);
	// 查询分页列表信息
	List<T> getPageList(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize);
	
}
