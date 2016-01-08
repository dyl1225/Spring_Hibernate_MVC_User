package com.dyl.dao.impl;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.dyl.dao.User1Dao;
import com.dyl.model.Role;
import com.dyl.model.User1;
@Repository("user1DaoImpl")
public class User1DaoImpl implements User1Dao
{

	private HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(User1 user1)
	{
		this.hibernateTemplate.save(user1);
	}

	public User1 loadById(int id)
	{
		return this.hibernateTemplate.load(User1.class, id);
	}

	public User1 fing(String username, String password)
	{
		return (User1) this.hibernateTemplate.getSessionFactory().getCurrentSession().
				createQuery("from User1 where username=? and password=?")
				.setParameter(0, username).setParameter(1, password).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User1> getAll()
	{
		return this.hibernateTemplate.find("from User1");
	}

	public void updateUser1Roles(User1 user1, Set<Role> roles)
	{
		user1.setRoles(null);
		user1.setRoles(roles);
		
		this.hibernateTemplate.update(user1);
	}
	public User1 loadByName(String username)
	{
		return (User1) this.hibernateTemplate.getSessionFactory().getCurrentSession().
				createQuery("from User1 where username=?")
				.setParameter(0, username).uniqueResult();
	}

}
