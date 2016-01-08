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
		System.out.println("ִ�е�½������");
		
		//1.�ȼ���û������Ƿ��Ѿ���¼��û�е�¼���Զ���¼
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		User u=(User) req.getSession().getAttribute("loginUser");

		//��Ϊ�����Զ���¼���û��б�ҳ�棬���ûص���½ҳ��
		if(u!=null)
		{
			chain.doFilter(req, resp);
			return;//Ҫ����return��������������ִ��
		}
		//2.û��¼������ǰ�Ƿ��е�½����cookie
		//���û���û�д������Զ���¼��cookie
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
		if(autoCookie==null)//�����ڱ��û�cookie
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
		//�û������Զ���¼��cookie,�ȼ��cookie����Ч�ڣ�
		String values[]=autoCookie.getValue().split("\\:");
		if(values.length!=3)
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
		
		//�õ�ʧЧʱ��ֵ
	    long expriesTime=	Long.parseLong(values[1]);
	    //�����½ʱ��ʧЧ�����µ�½
		if(System.currentTimeMillis()>expriesTime)
		{
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
		
		}
		
		//����ʧЧʱ����Ч���cookie�Ƿ���Ч
		String username=values[0];
		String client_md5=values[2];
		//�ڹ�������ͨ��spring�����Ļ�ȡbean
		ApplicationContext beans= WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
		UserDao userDao=(UserDao) beans.getBean("userDaoImpl");
		User user=userDao.loadUserByName(username);
		if(user==null)//���cookie�в�������û��������µ�½
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
		//���ڱ��û���ǰ��½��ûʧЧ��cookieִ���Զ���¼
		req.getSession().setAttribute("loginUser", user);
		chain.doFilter(req, resp); 
		}
		else//��û�е�½����Ҳû��cookie,�ص���ҳ�ȵ�¼
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
