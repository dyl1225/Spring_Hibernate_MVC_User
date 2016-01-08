package com.dyl.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dyl.model.SystemContext;

public class SystemContextFilter implements Filter
{

	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest hsr=(HttpServletRequest) request;
		int offset=0;
		
		try
		{
			//根据点击分页页码上的偏移量，获得
			offset = Integer.parseInt(hsr.getParameter("pager.offset"));
		}
		catch (NumberFormatException e)
		{
			// TODO: handle exception
		}
		try
		{
			SystemContext.setOffset(offset);
			SystemContext.setSize(1);
			chain.doFilter(request, response);
		}finally
		{
			SystemContext.removeOffSet();
			SystemContext.removeOffSize();
		}
	}

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
