package com.dyl.service;

import java.util.List;
import java.util.Set;
import com.dyl.model.Privilege;
import com.dyl.model.Resource;
import com.dyl.model.Role;
import com.dyl.model.User1;

public interface SecurityService
{
	// 1.�ṩ��Դ��صķ���
	public void addResource(Resource resource);

	public Resource findResource(String uri);

	public Resource findResourceById(int id);

	public List<Resource> getAllResource();
	public void updateResourcePrivilege(int resourceId,int privilegeId);
	// 2.�ṩȨ����صķ���
	public void addPrivilege(Privilege privilege);

	public Privilege loadPrivilegeById(int id);

	public List<Privilege> getAllPrivilege();
	// 3.j��ɫ��صķ���
	public void addRole(Role role);
	public Role loadRoleById(int id);
	public List<Role> getAllRole();
	public void updateRolePrivilege(int roleId,int[] privilegesId);
	// 4.�û���صķ���
	public void addUser1(User1 user1);
    public User1 loadUser1ById(int id);
	public User1 fingUser1(String username,String password);
	public List<User1> getAllUser1();
	public void updateUser1Roles(int user1Id,int[] rolesId);
	//�õ��û�������Ȩ��
	
	public Set<Privilege> getUser1Privileges(int userId);
	
}
