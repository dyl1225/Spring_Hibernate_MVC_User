package com.dyl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dyl.dao.UserDao;
import com.dyl.exception.UserException;
import com.dyl.model.Pager;
import com.dyl.model.User;
import com.dyl.service.UserService;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService
{

	private UserDao userDao;
	
	public UserDao getUserDao()
	{
		return userDao;
	}
	@Resource(name="userDaoImpl")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	public void add(User user)
	{
		User u=this.userDao.loadUserByName(user.getUsername());
		if(u!=null){
			throw new UserException("您添加的用户名已经存在");
		}
		userDao.add(user);
	}

	public void update(User user)
	{
		userDao.update(user);
	}

	public void delete(int id)
	{
		userDao.delete(id);
	}

	public User load(int id)
	{
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	public List<User> list()
	{
		// TODO Auto-generated method stub
		return userDao.list();
	}

	public Pager<User> find()
	{
		// TODO Auto-generated method stub
		return userDao.find();
	}

	public User login(String username, String password)
	{
		User u=this.userDao.loadUserByName(username);
		if(u==null) throw new UserException("登陆用户不存在");
		if(!u.getPassword().equals(password))
		{
			throw new UserException("用户密码不正确");
			
		}
		return u;
	}
	public User checkUser(String username, String password)
	{
		return this.userDao.checkUser(username, password);
	}

}
