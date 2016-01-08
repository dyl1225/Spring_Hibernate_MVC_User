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

	//用户列表页面
	@RequestMapping(value={"/users","/"},method=RequestMethod.GET)
	public String list(Model model)
	{
		model.addAttribute("pager", this.userService.find());
		return "user/list";
	}
	//用户添加操作
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
	
	//用户更新操作，某一项不能更新例如员工的录入时间，在这里我们设为密码不能更新，先把要更新的数据从数据库中取出。
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
