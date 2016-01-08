package com.dyl.dao;

import java.util.List;

import com.dyl.model.Pager;
import com.dyl.model.User;

public interface UserDao
{
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public User loadUserByName(String username);
	public User checkUser(String username,String password);
	public List<User> list();
	public Pager<User> find();
}
