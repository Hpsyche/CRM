package com.company.dao;

import com.company.domain.User;

public interface UserDao extends BaseDao<User>{
	
	User getByUserCode(String usercode);

}
