package com.dyl.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dyl.dao.RoleDao;
import com.dyl.model.Privilege;
import com.dyl.model.Role;
@Repository("roleDaoImpl")
public class RoleDaoImpl implements RoleDao
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

	public void add(Role role)
	{
		this.hibernateTemplate.save(role);
	}

	public Role loadById(int id)
	{
		return this.hibernateTemplate.load(Role.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Role> getAll()
	{
		return this.hibernateTemplate.find("from Role");
	}
	public void updateRolePrivilege(Role role, Set<Privilege> privileges)
	{
		//删除角色拥有的权限
		role.setPrivileges(null);
		
		role.setPrivileges(privileges);
		
		this.hibernateTemplate.update(role);
	}
	public Role loadByName(String name)
	{
		return (Role) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Role where name=?")
				.setParameter(0, name).uniqueResult();
				
	}

}
