package com.company.service.impl;

import com.company.dao.UserDao;
import com.company.domain.User;
import com.company.service.UserService;
import com.company.utils.MD5Utils;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Override
	public User getUserByCodePassword(User user) {
		
		// 根据用户user_code查找
		User resultUser=userDao.getByUserCode(user.getUser_code());
		// 如果返回null，抛出异常提示用户名错误
		if(resultUser==null){
			throw new RuntimeException("用户名不存在！");
		}
		// 比对密码，如果密码错误抛出异常提示
		if(!resultUser.getUser_password().equals(MD5Utils.md5(user.getUser_password()))){
			throw new RuntimeException("密码错误！");
		}
		// 返回查询到的用户
		return resultUser;
	}

	@Override
	public void saveUser(User user) {
		// 根据用户名查找判断用户名是否已经存在
		User resultUser = userDao.getByUserCode(user.getUser_code());
		if(resultUser!=null){
			throw new RuntimeException("用户名已存在！");
		}
		// 使用MD5加密用户密码
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state('1');
		userDao.save(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
