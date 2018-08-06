package com.company.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.company.dao.UserDao;
import com.company.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	@Override
	public User getByUserCode(final String usercode) {
		return getHibernateTemplate().execute(new HibernateCallback<User>() {

			@Override
			public User doInHibernate(Session session) throws HibernateException {
				// hql查询
				String hql="from User where user_code=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, usercode);
				User user=(User) query.uniqueResult();
				return user;
			}
		});
	}

}
