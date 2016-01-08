package com.dyl.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import sun.misc.BASE64Encoder;
import com.dyl.dao.UserDao;
import com.dyl.model.User;
@Component
public class LoginFilter implements Filter
{
    
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		System.out.println("执行登陆拦截器");
		
		//1.先检查用户本次是否已经登录，没有登录则自动登录
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		User u=(User) req.getSession().getAttribute("loginUser");

		//不为空则自动登录到用户列表页面，不用回到登陆页面
		if(u!=null)
		{
			chain.doFilter(req, resp);
			return;//要加上return；否则会继续往下执行
		}
		//2.没登录过则看以前是否有登陆过的cookie
		//看用户有没有带回来自动登录的cookie
		Cookie autoCookie=null;
		Cookie[] cookies=req.getCookies();
		if(cookies!=null)
		{
		for(int i=0;i<cookies.length;i++)
		{
			if(cookies[i].getName().equals("autologin"))
			{
				autoCookie=cookies[i];
			}
		}
		if(autoCookie==null)//不存在本用户cookie
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
		//用户带了自动登录的cookie,先检查cookie的有效期，
		String values[]=autoCookie.getValue().split("\\:");
		if(values.length!=3)
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
		
		//得到失效时间值
	    long expriesTime=	Long.parseLong(values[1]);
	    //如果登陆时间失效，重新登陆
		if(System.currentTimeMillis()>expriesTime)
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
		
		}
		
		//代表失效时间有效检查cookie是否有效
		String username=values[0];
		String client_md5=values[2];
		//在过滤器中通过spring下上文获取bean
		ApplicationContext beans= WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
		UserDao userDao=(UserDao) beans.getBean("userDaoImpl");
		User user=userDao.loadUserByName(username);
		if(user==null)//如果cookie中不是这个用户，则重新登陆
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
	
		}
		String server_md5=null;
		try
		{
			server_md5 = md5(user.getUsername(), user.getPassword(), expriesTime);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		if(!server_md5.equals(client_md5))
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
		
		}
		//存在本用户以前登陆的没失效的cookie执行自动登录
		req.getSession().setAttribute("loginUser", user);
		chain.doFilter(req, resp); 
		}
		else//既没有登陆过，也没有cookie,回到首页先登录
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
	}
	public String md5(String username,String password,long expriestime) throws NoSuchAlgorithmException
	{
		String value=password+":"+expriestime+":"+username;
		MessageDigest md=MessageDigest.getInstance("md5");
		byte[] md5=md.digest(value.getBytes());
		BASE64Encoder encoder=new BASE64Encoder();
		return encoder.encode(md5);
	}
	public void destroy()
	{

	}

	public void init(FilterConfig filterConfig) throws ServletException
	{

	}
}
