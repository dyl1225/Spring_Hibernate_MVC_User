package com.dyl.service;

import java.util.List;
import java.util.Set;
import com.dyl.model.Privilege;
import com.dyl.model.Resource;
import com.dyl.model.Role;
import com.dyl.model.User1;

public interface SecurityService
{
	// 1.提供资源相关的服务
	public void addResource(Resource resource);

	public Resource findResource(String uri);

	public Resource findResourceById(int id);

	public List<Resource> getAllResource();
	public void updateResourcePrivilege(int resourceId,int privilegeId);
	// 2.提供权限相关的服务
	public void addPrivilege(Privilege privilege);

	public Privilege loadPrivilegeById(int id);

	public List<Privilege> getAllPrivilege();
	// 3.j角色相关的服务
	public void addRole(Role role);
	public Role loadRoleById(int id);
	public List<Role> getAllRole();
	public void updateRolePrivilege(int roleId,int[] privilegesId);
	// 4.用户相关的服务
	public void addUser1(User1 user1);
    public User1 loadUser1ById(int id);
	public User1 fingUser1(String username,String password);
	public List<User1> getAllUser1();
	public void updateUser1Roles(int user1Id,int[] rolesId);
	//得到用户的所有权限
	
	public Set<Privilege> getUser1Privileges(int userId);
	
}
