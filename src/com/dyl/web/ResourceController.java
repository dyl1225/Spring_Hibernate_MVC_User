package com.dyl.web;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dyl.model.Privilege;
import com.dyl.model.Resource;
import com.dyl.service.SecurityService;
@Controller
@RequestMapping("/resource")
public class ResourceController
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
		
		model.addAttribute("list", this.securityService.getAllResource());
		return "security/listresource";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model)
	{
		model.addAttribute("resource", new Resource());
		return "security/addresource";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Resource resource,BindingResult br)
	{
		
		if(br.hasErrors())
		{
			return "security/addresource";
		}
		
		this.securityService.addResource(resource);
		
		return "redirect:/resource/getAll";
		
	}
	
	//修改资源的权限
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model)
	{
		Resource r=this.securityService.findResourceById(id);
		//得到系统的全部权限
		List<Privilege> list=this.securityService.getAllPrivilege();
		model.addAttribute("resource", r);
		model.addAttribute("list", list);
		//跳转到更新资源权限页面
		
		return "security/updateResourcePrivilege";
		
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public String update(String rid,String pid, Model model)
	{
		int resourceId=Integer.parseInt(rid);
		int privilegeId=Integer.parseInt(pid);
		this.securityService.updateResourcePrivilege(resourceId, privilegeId);
		return "redirect:/resource/getAll";
		
	}
}
