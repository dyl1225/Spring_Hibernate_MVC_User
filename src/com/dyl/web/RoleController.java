package com.dyl.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dyl.exception.UserException;
import com.dyl.model.Privilege;
import com.dyl.model.Role;
import com.dyl.service.SecurityService;
@Controller
@RequestMapping("/role")
public class RoleController
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
		
		model.addAttribute("list", this.securityService.getAllRole());
		return "security/listrole";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model)
	{
		model.addAttribute("role", new Role());
		return "security/addrole";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Role role,BindingResult br)
	{
		
		if(br.hasErrors())
		{
			return "security/addrole";
		}
		
		this.securityService.addRole(role);
		
		return "redirect:/role/getAll";
		
	}
	
	//�޸Ľ�ɫ��Ȩ��
	
	@RequestMapping(value="/{id}/updatePrivilege",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model)
	{
		Role r=this.securityService.loadRoleById(id);
		//�õ�ϵͳ��ȫ��Ȩ��
		List<Privilege> list=this.securityService.getAllPrivilege();
		model.addAttribute("role", r);
		model.addAttribute("list", list);
		//��ת��������ԴȨ��ҳ��
		
		return "security/updateRolePrivilege";
		
	}
	
	@RequestMapping(value="/{id}/updatePrivilege",method=RequestMethod.POST)
	public String update(@RequestParam("roleid")String roleid, Model model,HttpServletRequest request)
	{
		int roleId=Integer.parseInt(roleid);
		//���ո�ѡ���е�����
		String[] pids=request.getParameterValues("pid");
		if(pids==null||pids.length==0)
		{
			throw new UserException("��û��ѡ���κ�Ȩ��");
		}
		int[] privilegesId = null;
		for(int i=0;i<pids.length;i++)
		{
			privilegesId[i]=Integer.parseInt(pids[i]);
		}
		
		this.securityService.updateRolePrivilege(roleId, privilegesId);
		return "redirect:/role/getAll";
		
	}
}
