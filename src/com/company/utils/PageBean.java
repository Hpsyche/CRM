package com.company.utils;

import java.util.List;

public class PageBean<T> {

	// 总记录数，Dao查询得到
	private Integer totalCount;
	// 总页数，计算得到
	private Integer totalPage;
	// 每页记录数，前端传入
	private Integer pageSize;
	// 当前页数，前端传入
	private Integer currentPage;
	// 数据列表，Dao查询得到
	private List<T> list;

	public PageBean(Integer totalCount, Integer currentPage, Integer pageSize) {
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		// 若当前页面为空，默认为1；
		if (this.currentPage == null) {
			this.currentPage = 1;
		}
		// 若每页记录数为空，默认为5；
		if (this.pageSize == null) {
			this.pageSize = 5;
		}

		// 计算总页数
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

		// 处理非法currentPage请求
		if (this.currentPage < 1) {
			this.currentPage = 1;
		} else if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}

	}
	
	// 计算list元素在数据库的起始索引
	public Integer getStartIndex(){
		return (this.currentPage-1)*this.pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
