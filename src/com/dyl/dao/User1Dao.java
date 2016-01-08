package com.dyl.dao;

import java.util.List;
import java.util.Set;

import com.dyl.model.Role;
import com.dyl.model.User1;

public interface User1Dao
{
	public void add(User1 user1);
	
	public User1 loadById(int id);
	public User1 loadByName(String username);
	public User1 fing(String username,String password);
	
	public List<User1> getAll();
	
	public void updateUser1Roles(User1 user1,Set<Role> roles);
}
