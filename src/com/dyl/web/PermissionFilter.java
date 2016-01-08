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
		
		//1,����û��Ƿ��½
		User1 user1=(User1) request.getSession().getAttribute("user1");
				
		//2.û��¼ִ�е�½
		if(user1==null)
		{
			request.setAttribute("message", "���ȵ�¼");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//3.�õ��û���Ҫ���ʵ���Դ
		String uri=request.getRequestURI();
		
		//4.�õ����ʸ���Դ����Ҫ��Ȩ��
		
		ApplicationContext beans= WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		
		SecurityService ssi=(SecurityServiceImpl) beans.getBean("ssi");
		
		Resource r=ssi.findResource(uri);
		if(r==null)
		{
			chain.doFilter(request, response);
			return ;
		}
		//�õ����ʸ���Դ����������Ҫ��Ȩ��
		Privilege p=r.getPrivilege();
		
		//5.�ж��û��Ƿ�����Ӧ��Ȩ��
		
		//�ĵ��û����е�Ȩ��
		
		Set<Privilege> privileges=ssi.getUser1Privileges(user1.getId());
		//6.���û��Ȩ�ޣ�����ϵ����Ա
		if(!privileges.contains(p))//Privilege���븲дequals��hashcode����
		{
			request.setAttribute("message", "�Բ�����û��Ȩ�޷��ʣ�����ϵ����Ա");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//7.��Ȩ�������
		chain.doFilter(request, response);
		
	}

	public void destroy()
	{

	}

}
