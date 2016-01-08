package com.dyl.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.dyl.model.Privilege;
import com.dyl.model.User1;
import com.dyl.service.SecurityService;
import com.dyl.service.impl.SecurityServiceImpl;

public class PermissionTag extends SimpleTagSupport {

	private String value;
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public void doTag() throws JspException, IOException {
		
		//关断用户拥有权限值中，是否包含value
		PageContext pagecontext = (PageContext) this.getJspContext();
		HttpSession session = pagecontext.getSession();
		User1 user1 = (User1) session.getAttribute("user1");
		if(user1!=null){

			ApplicationContext beans= WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
			SecurityService ssi=(SecurityServiceImpl) beans.getBean("ssi");
			List<Privilege> privileges = (List<Privilege>) ssi.getUser1Privileges(user1.getId());
			boolean b = false;
			for(Privilege p : privileges){
				if(p.getName().equals(value)){
					b = true;
					break;
				}
			}
			if(b){
				this.getJspBody().invoke(null);
			}
		}	
	}
}
