package com.dyl.dao;

import java.util.List;
import java.util.Set;

import com.dyl.model.Privilege;
import com.dyl.model.Role;

public interface RoleDao
{
	public void add(Role role);
	//���ҽ�ɫ�Ļ�����Ϣ���ҳ���ɫ��Ȩ��
	public Role loadById(int id);
	
	public Role loadByName(String name);
	
	public List<Role> getAll();
	
	//����ĳ����ɫ��Ȩ��
	public void updateRolePrivilege(Role role,Set<Privilege> privileges);
}
