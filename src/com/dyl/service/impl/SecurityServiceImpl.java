package com.dyl.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.dyl.dao.PrivilegeDao;
import com.dyl.dao.ResourceDao;
import com.dyl.dao.RoleDao;
import com.dyl.dao.User1Dao;
import com.dyl.exception.UserException;
import com.dyl.model.Privilege;
import com.dyl.model.Resource;
import com.dyl.model.Role;
import com.dyl.model.User1;
import com.dyl.service.SecurityService;
@Service("ssi")
public class SecurityServiceImpl implements SecurityService
{
	private PrivilegeDao privilegeDao;
	
	private ResourceDao resourceDao;
	
	private RoleDao roleDao;
	
	private User1Dao user1Dao;
	
	public PrivilegeDao getPrivilegeDao()
	{
		return privilegeDao;
	}
	@javax.annotation.Resource(name="privilegeDaoImpl")
	public void setPrivilegeDao(PrivilegeDao privilegeDao)
	{
		this.privilegeDao = privilegeDao;
	}

	public ResourceDao getResourceDao()
	{
		return resourceDao;
	}
	@javax.annotation.Resource(name="resourceDaoImpl")
	public void setResourceDao(ResourceDao resourceDao)
	{
		this.resourceDao = resourceDao;
	}

	public RoleDao getRoleDao()
	{
		return roleDao;
	}
	@javax.annotation.Resource(name="roleDaoImpl")
	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}

	public User1Dao getUser1Dao()
	{
		return user1Dao;
	}
	@javax.annotation.Resource(name="user1DaoImpl")
	public void setUser1Dao(User1Dao user1Dao)
	{
		this.user1Dao = user1Dao;
	}
	/***************************************************
	 * // 1.提供资源相关的服务
	 * 
	 ***************************************************/
	
	public void addResource(Resource resource)
	{
		Resource r=this.resourceDao.find(resource.getUri());
		if(r!=null)
		{
			throw new UserException("资源已经存在，不能重复添加");
		}
		
		this.resourceDao.add(resource);
	}

	public Resource findResource(String uri)
	{
		return this.resourceDao.find(uri);
	}

	public Resource findResourceById(int id)
	{
		return this.resourceDao.findById(id);
	}

	public List<Resource> getAllResource()
	{
		return this.resourceDao.getAll();
	}
	//更新控制资源的权限
	public void updateResourcePrivilege(int resourceId, int privilegeId)
	{
		Resource r=this.resourceDao.findById(resourceId);
		Privilege p=this.privilegeDao.loadById(privilegeId);
		this.resourceDao.updatePrivilege(r, p);
		
	}
	// 2.提供权限相关的服务
	public void addPrivilege(Privilege privilege)
	{
		this.privilegeDao.add(privilege);
	}

	public Privilege loadPrivilegeById(int id)
	{
		return this.privilegeDao.loadById(id);
	}

	public List<Privilege> getAllPrivilege()
	{
		return this.privilegeDao.getAll();
	}
	// 3.j角色相关的服务
	public void addRole(Role role)
	{
		Role r=this.roleDao.loadByName(role.getName());
		if(r!=null)
		{
			throw new UserException("角色已经存在！");
		}
	}

	public Role loadRoleById(int id)
	{
		return this.roleDao.loadById(id);
	}

	public List<Role> getAllRole()
	{
		return this.roleDao.getAll();
	}

	public void updateRolePrivilege(int roleId, int[] privilegesId)
	{
		Role r=this.roleDao.loadById(roleId);
		Set<Privilege> set=new HashSet<Privilege>();
		for(int i=0;privilegesId!=null&&i<privilegesId.length;i++)
		{
			Privilege p=this.privilegeDao.loadById(privilegesId[i]);
			set.add(p);
		}
      this.roleDao.updateRolePrivilege(r, set);
	}
	
	// 4.用户相关的服务
	public void addUser1(User1 user1)
	{
		User1 u=this.user1Dao.loadByName(user1.getUsername());
		if(u!=null)
		{
			throw new UserException("你添加的用户名已存在");
		}
		
		this.user1Dao.add(user1);
	}
	public User1 loadUser1ById(int id)
	{
		return this.user1Dao.loadById(id);
	}
	public User1 fingUser1(String username, String password)
	{
		return this.user1Dao.fing(username, password);
	}
	public List<User1> getAllUser1()
	{
		return this.user1Dao.getAll();
	}
	public void updateUser1Roles(int user1Id, int[] rolesId)
	{
			User1 u=this.user1Dao.loadById(user1Id);
			Set<Role> sets=new HashSet<Role>();
			
			for(int i=0;rolesId!=null&&i<rolesId.length;i++)
			{
				Role r=this.roleDao.loadById(rolesId[i]);
				
				sets.add(r);
				
			}
	this.user1Dao.updateUser1Roles(u, sets);		
	}
	public Set<Privilege> getUser1Privileges(int userId)
	{
		Set<Privilege> allPrivilege=new HashSet<Privilege>();
		
		User1 u=this.user1Dao.loadById(userId);
		
		Set<Role> roles=u.getRoles();
		
		for(Role r:roles)
		{
			r=this.roleDao.loadById(r.getId());
		    Set<Privilege> privileges=r.getPrivileges();
		    
		    allPrivilege.addAll(privileges);
		}
		
		
		return allPrivilege;
	}
	
	

}
