package com.dyl.dao;

import java.util.List;

import com.dyl.model.Privilege;

public interface PrivilegeDao
{
	public void add(Privilege privilege);
	
	public Privilege loadById(int id);
	
	public List<Privilege> getAll();
}
