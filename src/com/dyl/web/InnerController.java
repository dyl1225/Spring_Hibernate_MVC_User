package com.dyl.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sun.misc.BASE64Encoder;

import com.dyl.model.User;
import com.dyl.service.UserService;

@Controller
@SessionAttributes("loginUser")//用户放到session中
public class InnerController
{

	private UserService userService;

	public UserService getUserService()
	{
		return userService;
	}
	@Resource(name="userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	//用户登陆
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login()
	{
		
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,@RequestParam(value="time",required=false,defaultValue="0" ) String time,String mark,HttpServletRequest request,
			Model model,HttpServletResponse response)
	{
		User u=userService.login(username, password);
		int cookietime=Integer.parseInt(time);
	//	Cookie cookie=makeCookie(u, cookietime);
	//	response.addCookie(cookie);
	//	model.addAttribute("loginUser", u);
		//给客户机发送自动登录的cookie
	//	return "redirect:/user/users";
		//if ("mark".equals(mark))
		//{
			// 声明 cookie
			Cookie autoCookie = null;
			// 获取所有的　ｃｏｏｋｉｅ
			Cookie cookies[] = request.getCookies();
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 判断是否存在自动登录记录
				if ("autologin".equals(cookie.getName())) {
					autoCookie = cookie;// 赋值
					// 当cookie 存在的时候 我需要重新设置值
					long time1 = System.currentTimeMillis() + cookietime
							* 1000;
					String cookieValue=username+":"+time1+
							":"+md5(username, password, time1);
					cookie.setValue(cookieValue);
				} else {
					// 不在创建
					long time2 = System.currentTimeMillis() + cookietime
							* 1000;
					String cookieValue=username+":"+time2+
							":"+md5(username, password, time2);
					autoCookie = new Cookie("autologin", cookieValue);
				}
			}

			autoCookie.setMaxAge(cookietime);// 10 分钟
			autoCookie.setPath("/Spring_Hibernate_MVC_User");
			response.addCookie(autoCookie);// 添加里边去了
	//	}
	
		// user 存入到 session域中，也放到了request请求域中
		model.addAttribute("loginUser", u);
		return "redirect:/user/users";
	}
		
	

	public Cookie makeCookie(User user,int expriestime)
	{
		long currentTime=System.currentTimeMillis();
		
			String cookieValue=user.getUsername()+":"+(currentTime+expriestime*1000)+
					":"+md5(user.getUsername(), user.getPassword(), currentTime+expriestime*1000);
			Cookie cookie=new Cookie("autologin", cookieValue);
			cookie.setMaxAge(expriestime);
			cookie.setPath("/Spring_Hibernate_MVC_User");
			return cookie;
		
		
		
	}
	public String md5(String username,String password,long expriestime)
	{
		String value=password+":"+expriestime+":"+username;
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("md5");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		byte[] md5=md.digest(value.getBytes());
		BASE64Encoder encoder=new BASE64Encoder();
		return encoder.encode(md5);
	}
	@RequestMapping(value="/logout")
	public String logout(Model model,HttpSession session)
	{
		
		model.asMap().remove("loginUser");
		session.invalidate();
		
		
		
		return "login";
	}
	
	
}
