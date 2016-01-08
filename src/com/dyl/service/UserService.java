package com.dyl.service;

import java.util.List;

import com.dyl.model.Pager;
import com.dyl.model.User;

public interface UserService
{
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public List<User> list();
	public Pager<User> find();
	public User login(String username,String password);
	public User checkUser(String username,String password);
}
