package com.company.web.action;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.domain.User;
import com.company.service.UserService;
import com.company.utils.VerifyCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	// 用于接收封装表单参数
	private User user=new User();
	private UserService userService;
	
	// 属性驱动接收验证码
	private String verifyCode;
	
	public String generateVerifyCode() throws Exception {
		
		VerifyCode vc=new VerifyCode();
		
		// 获得验证码图片
		BufferedImage image = vc.getImage();
		
		// 获得验证码文本
		String verifyText = vc.getText();
		
		// 将验证码保存到session域中
		ActionContext.getContext().getSession().put("verifyText", verifyText);
		
		// 将验证码图片输出到页面
		HttpServletResponse response = ServletActionContext.getResponse();
		VerifyCode.output(image, response.getOutputStream());
		return null;
	}

	public String login() throws Exception {
		// 校验验证码
		String verifyText = (String) ActionContext.getContext().getSession().get("verifyText");
		if(!verifyText.equalsIgnoreCase(verifyCode)){
			throw new RuntimeException("验证码错误！");
		}
		//调用userService的登陆方法
		User resultUser = userService.getUserByCodePassword(user);
		//将返回的user对象保存到session域
		ActionContext.getContext().getSession().put("user", resultUser);
		//重定向到首页
		return "toHome";
	}
	
	/*
	 * 退出登陆
	 */
	public String exit() throws Exception {
		
		// 将 session域中的user置为null
		ActionContext.getContext().getSession().put("user", null);
		//重定向到登陆页面
		return "toLogin";
	}

	public String regist() throws Exception {
		// 校验验证码
		String verifyText = (String) ActionContext.getContext().getSession().get("verifyText");
		if(!verifyText.equalsIgnoreCase(verifyCode)){
			ActionContext.getContext().put("error_msg", "验证码错误！");
			return "registerror";
		}
		// 调用service保存用户
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			ActionContext.getContext().put("error_msg", e.getMessage());
			return "registerror";
		}
		// 重定向到登陆页面
		return "toLogin";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
