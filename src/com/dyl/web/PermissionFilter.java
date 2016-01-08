package com.dyl.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dyl.model.Privilege;
import com.dyl.model.Resource;
import com.dyl.model.User1;
import com.dyl.service.SecurityService;
import com.dyl.service.impl.SecurityServiceImpl;

public class PermissionFilter implements Filter
{

	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//1,检查用户是否登陆
		User1 user1=(User1) request.getSession().getAttribute("user1");
				
		//2.没登录执行登陆
		if(user1==null)
		{
			request.setAttribute("message", "请先登录");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//3.得到用户想要访问的资源
		String uri=request.getRequestURI();
		
		//4.得到访问该资源所需要的权限
		
		ApplicationContext beans= WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		
		SecurityService ssi=(SecurityServiceImpl) beans.getBean("ssi");
		
		Resource r=ssi.findResource(uri);
		if(r==null)
		{
			chain.doFilter(request, response);
			return ;
		}
		//得到访问该资源所必须所需要的权限
		Privilege p=r.getPrivilege();
		
		//5.判断用户是否有相应的权限
		
		//的道用户所有的权限
		
		Set<Privilege> privileges=ssi.getUser1Privileges(user1.getId());
		//6.如果没有权限，请联系管理员
		if(!privileges.contains(p))//Privilege必须覆写equals与hashcode方法
		{
			request.setAttribute("message", "对不起，你没有权限访问，请联系管理员");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//7.有权限则放行
		chain.doFilter(request, response);
		
	}

	public void destroy()
	{

	}

}
