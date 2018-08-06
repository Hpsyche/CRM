package com.company.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.company.domain.BaseDict;
import com.company.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseDictAction extends ActionSupport {
	
	private BaseDictService baseDictService;
	private String dict_type_code;
	
	@Override
	public String execute() throws Exception {
		
		// 调用Service查询字典列表
		List<BaseDict> list=baseDictService.getListByTypeCode(dict_type_code);
		// 将字典列表转换成json串
		String json = JSONArray.fromObject(list).toString();
		// 将json串输出到页面
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
}
