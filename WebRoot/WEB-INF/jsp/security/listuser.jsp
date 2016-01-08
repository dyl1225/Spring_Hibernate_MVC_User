<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
  </head>
  
  <body style="text-align: center;">
   	<br/>	<br/>
   	
   	<table width="80%" align="center">
   		<tr>
   			<td></td>
   			<td></td>
   			<td align="right">
   				<a href="${pageContext.request.contextPath }/user1/add">添加用户</a>
   			</td>
   		</tr>
   	</table>
   	<table width="80%" frame="border" align="center" border="1" bordercolor="red">
   		<tr>
   			<td>用户名称</td>
   			<td>用户密码</td>
   			<td>用户昵称</td>
   			<td>用户邮箱</td>
   			<td>操作</td>
   		</tr>
   		
   		<c:forEach var="user" items="${list}">
   			<tr>
	   			<td>${user.username }</td>
	   			<td>${user.password }</td>
	   			<td>${user.nickname }</td>
	   			<td>${user.email }</td>
	   			<td>
	   				<a href="${pageContext.request.contextPath }/user1/${user.id }/updateUserRole">为用户授予角色</a>
	   				<a href="${pageContext.request.contextPath }/user1/${user.id }/delete">删除用户</a>
	   				<a href="${pageContext.request.contextPath }/user1/${user.id }/update">修改用户信息</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
   	
  </body>
</html>

