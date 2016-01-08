package com.dyl.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dyl.dao.ResourceDao;
import com.dyl.model.Privilege;
import com.dyl.model.Resource;
@Repository("resourceDaoImpl")
public class ResourceDaoImpl implements ResourceDao
{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}
	@javax.annotation.Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Resource resource)
	{
		this.hibernateTemplate.save(resource);
	}

	public Resource find(String uri)
	{
		return (Resource) this.hibernateTemplate.getSessionFactory().getCurrentSession().
				createQuery("from Resource where uri=?").setParameter(0, uri).uniqueResult();
	}

	public Resource findById(int id)
	{
		return this.hibernateTemplate.load(Resource.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAll()
	{
		return this.hibernateTemplate.find("from Resource");
	}

	public void updatePrivilege(Resource resource, Privilege privilege)
	{
//		this.hibernateTemplate.getSessionFactory().getCurrentSession()
//		.createQuery("update Resource r set r.privilege.id=? where r.id=?")
//		.setParameter(0, privilege.getId())
//		.setParameter(1, resource.getId())
//		.executeUpdate();
		resource.setPrivilege(privilege);
		this.hibernateTemplate.update(resource);
		
	}

}
