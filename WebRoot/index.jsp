<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/itcast.tld" prefix="itcast" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页</title>
  </head>
  
  <body>
   <br>
   <br>
              欢迎您：${user1.username }   
    <a href="${pageContext.request.contextPath }/user1/logout">注销</a>
   
    <br/><br/>
	<a href="${pageContext.request.contextPath }/login.jsp">登陆</a>    
    <br/><br/>
    
    <itcast:permission value="添加分类">  <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
    	<a href="/Spring_Hibernate_MVC_User/manager/Servlet1">添加分类</a>
    </itcast:permission>
    
    <itcast:permission value="删除分类">  <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
    	 <a href="/Spring_Hibernate_MVC_User/manager/Servlet2">删除分类</a>
    </itcast:permission>
    
    
    <itcast:permission value="修改分类">  <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
    	<a href="/Spring_Hibernate_MVC_User/manager/Servlet3">修改分类</a>
    </itcast:permission>
    
    <itcast:permission value="查找分类">  <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
    	<a href="/Spring_Hibernate_MVC_User/manager/Servlet4">查找分类</a>
    </itcast:permission>
    
    <itcast:permission value="删除商品">  <!-- 标签控制用户需要有添加分类的权限值，才可以看到超链接 -->
    	<a href="/Spring_Hibernate_MVC_User/manager/Servlet5">删除商品</a>
    </itcast:permission>
    
  </body>
</html>
