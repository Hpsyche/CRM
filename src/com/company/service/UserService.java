package com.company.service;

import com.company.domain.User;

public interface UserService {

	// 登陆方法
	User getUserByCodePassword(User user);
	
	
	// 注册方法
	void saveUser(User user);
	
}
