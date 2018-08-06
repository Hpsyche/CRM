package com.company.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.company.dao.CustomerDao;
import com.company.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public List<Object[]> getCount(final String query) {
		
		return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			String sql="SELECT bd.dict_item_name, COUNT(bd.dict_item_name) "
							+ "FROM base_dict bd INNER JOIN cst_customer cc "
							+ "WHERE bd.dict_id=cc."+query
							+ " GROUP BY bd.dict_item_name";
			@SuppressWarnings("unchecked")
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				
				return list;
			}
			
		});
		
	}

}
