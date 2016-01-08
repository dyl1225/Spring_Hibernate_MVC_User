package com.dyl.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dyl.exception.UserException;
import com.dyl.model.Role;
import com.dyl.model.User1;
import com.dyl.service.SecurityService;
@Controller
@SessionAttributes("user1")
@RequestMapping("/user1")
public class User1Controller
{
	private SecurityService securityService;
	
	
	
	public SecurityService getSecurityService()
	{
		return securityService;
	}


	@javax.annotation.Resource(name="ssi")
	public void setSecurityService(SecurityService securityService)
	{
		this.securityService = securityService;
	}



	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public String getAll(Model model)
	{
		
		model.addAttribute("list", this.securityService.getAllUser1());
		return "security/listuser";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model)
	{
		model.addAttribute("user1", new User1());
		return "security/adduser";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User1 user1,BindingResult br)
	{
		
		if(br.hasErrors())
		{
			return "security/adduser";
		}
		
		this.securityService.addUser1(user1);
		
		return "redirect:/user1/getAll";
		
	}
	
	//�޸��û���ɫ
	
	@RequestMapping(value="/{id}/updateUserRole",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model)
	{
		User1 user1=this.securityService.loadUser1ById(id);
		//�õ�ϵͳ��ȫ��Ȩ��
		List<Role> list=this.securityService.getAllRole();
		model.addAttribute("user1", user1);
		model.addAttribute("list", list);
		//��ת��������ԴȨ��ҳ��
		
		return "security/updateUserRole";
		
	}
	
	@RequestMapping(value="/{id}/updateUserRole",method=RequestMethod.POST)
	public String update(@RequestParam("userid")String userid, Model model,HttpServletRequest request)
	{
		int userId=Integer.parseInt(userid);
		//���ո�ѡ���е�����
		String[] rids=request.getParameterValues("rid");
		if(rids==null||rids.length==0)
		{
			throw new UserException("��û��ѡ���κ�Ȩ��");
		}
		int[] rolesId = new int[rids.length];
		for(int i=0;i<rids.length;i++)
		{
			rolesId[i]=Integer.parseInt(rids[i]);
		}
		
		this.securityService.updateUser1Roles(userId, rolesId);
		return "redirect:/user1/getAll";
		
	}
	
	//�û���½
		@RequestMapping(value="/login",method=RequestMethod.GET)
		public String login()
		{
			
			return "login";
		}
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public String login(String username,String password,HttpServletRequest request,
				Model model,HttpServletResponse response)
		{
			User1 u=securityService.fingUser1(username, password);
			if(u==null)
			{
				throw new UserException("�û������������");
			}
			model.addAttribute("user1", u);
			//��½�ɹ�����ת����ҳ
			return "redirect:/Spring_Hibernate_MVC_User/index.jsp";
	
		}
		@RequestMapping(value="/logout")
		public String logout(Model model,HttpSession session)
		{
			
			model.asMap().remove("user1");
			session.invalidate();
			
			return "redirect:/Spring_Hibernate_MVC_User/index.jsp";
		}
		
}
