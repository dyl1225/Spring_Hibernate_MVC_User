package com.dyl.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dyl.model.User;
import com.dyl.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
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

	//�û��б�ҳ��
	@RequestMapping(value={"/users","/"},method=RequestMethod.GET)
	public String list(Model model)
	{
		model.addAttribute("pager", this.userService.find());
		return "user/list";
	}
	//�û���Ӳ���
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model)
	{
		model.addAttribute("user",new User());
		
		return "user/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user,BindingResult br)
	{
		if(br.hasErrors())
		{
			return "user/add";
		}
		System.out.println(user);
		userService.add(user);
		
		return "redirect:/user/users";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model)
	{
		model.addAttribute("user", this.userService.load(id));
		return "user/show";
	}
	
	//�û����²�����ĳһ��ܸ�������Ա����¼��ʱ�䣬������������Ϊ���벻�ܸ��£��Ȱ�Ҫ���µ����ݴ����ݿ���ȡ����
		@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
		public String update(@PathVariable int id, Model model)
		{
			model.addAttribute(this.userService.load(id));
			
			return "user/update";
		}
		@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
		public String update(@PathVariable int id, @Validated User user,BindingResult br,Model model)
		{
			if(br.hasErrors())
			{
				return "user/update";
			}
			User u=userService.load(id);
			u.setPassword(user.getPassword());
			u.setEmail(user.getEmail());
			u.setNickname(user.getNickname());
			u.setUsername(user.getUsername());
			userService.update(u);
			
			return "redirect:/user/users";
		}
		
		@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
		public String delete(@PathVariable int id, Model model)
		{
			userService.delete(id);
			return "redirect:/user/users";
		}
		
}
