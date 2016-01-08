package com.dyl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dyl.dao.PrivilegeDao;
import com.dyl.model.Privilege;
@Repository("privilegeDaoImpl")
public class PrivilegeDaoImpl implements PrivilegeDao
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

	public void add(Privilege privilege)
	{
		this.hibernateTemplate.save(privilege);
	}

	public Privilege loadById(int id)
	{
		return this.hibernateTemplate.load(Privilege.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Privilege> getAll()
	{
		return this.hibernateTemplate.find("from Privilege");
	}

}
