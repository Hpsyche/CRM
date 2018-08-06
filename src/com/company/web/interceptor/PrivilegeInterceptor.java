package com.company.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		// 获取session中的登陆标识
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 判断登陆标识状态并判断
		Object object = session.get("user");
		if(object!=null){//放行
			return invocation.invoke();
		}else{//重定向到登陆界面
			return "toLogin";
		}
	}

}
