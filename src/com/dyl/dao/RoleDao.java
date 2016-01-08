package com.dyl.dao;

import java.util.List;
import java.util.Set;

import com.dyl.model.Privilege;
import com.dyl.model.Role;

public interface RoleDao
{
	public void add(Role role);
	//查找角色的基本信息，找出角色的权限
	public Role loadById(int id);
	
	public Role loadByName(String name);
	
	public List<Role> getAll();
	
	//更新某个角色的权限
	public void updateRolePrivilege(Role role,Set<Privilege> privileges);
}
