package com.dyl.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dyl.model.Privilege;
import com.dyl.service.SecurityService;


@Controller
@RequestMapping("/privilege")
public class PrivilegeController
{
	private SecurityService securityService;

	public SecurityService getSecurityService()
	{
		return securityService;
	}
	@Resource(name="ssi")
	public void setSecurityService(SecurityService securityService)
	{
		this.securityService = securityService;
	}
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public String getAll(Model model)
	{
		model.addAttribute("list", this.securityService.getAllPrivilege());
		return "security/listprivilge";
		
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model)
	{
		
		model.addAttribute("privilege", new Privilege());
		
		return "security/addprivilege";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Privilege privilege,BindingResult br)
	{
		if(br.hasErrors())
		{
			return "security/addprivilege";
		}
		
		this.securityService.addPrivilege(privilege);
		
		return "redirect:/privilege/getAll";
	}
	
}
