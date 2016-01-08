package com.dyl.dao;

import java.util.List;

import com.dyl.model.Privilege;
import com.dyl.model.Resource;


public interface ResourceDao
{
	public void add(Resource resource);
	public Resource find(String uri);
	public Resource findById(int id);
	public List<Resource> getAll();
	public void updatePrivilege(Resource resource,Privilege privilege);
}
