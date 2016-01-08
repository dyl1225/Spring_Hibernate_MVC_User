<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
  </head>
  
  <body style="text-align: center;">
   	<br/>	<br/>
   	
   	<table width="80%">
   		<tr>
   			<td></td>
   			<td></td>
   			<td align="right">
   				<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=addUI">添加用户</a>
   			</td>
   		</tr>
   	</table>
   	<table width="80%" frame="border">
   		<tr>
   			<td>用户名称</td>
   			<td>用户密码</td>
   			<td>用户描述</td>
   			<td>操作</td>
   		</tr>
   		
   		<c:forEach var="user" items="${list}">
   			<tr>
	   			<td>${user.username }</td>
	   			<td>${user.password }</td>
	   			<td>${user.description }</td>
	   			<td>
	   				<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=forUpdateUserRoleUI&id=${user.id }">为用户授予角色</a>
	   				<a href="#">删除</a>
	   				<a href="#">修改</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
   	
  </body>
</html>

