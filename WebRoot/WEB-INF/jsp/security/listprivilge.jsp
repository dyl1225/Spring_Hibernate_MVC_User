<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限列表</title>
  </head>
  
  <body style="text-align: center;">
   	<br/>	<br/>
   	
   	<table width="700" align="center">
   		<tr>
   			<td></td>
   			<td></td>
   			<td align="right">
   				<a href="${pageContext.request.contextPath }/privilege/add">添加权限</a>
   			</td>
   		</tr>
   	</table>
   	<table width="60%" frame="border" align="center" border="1" bordercolor="blue">
   		<tr>
   			<td>权限名称</td>
   			<td>权限描述</td>
   			<td>操作</td>
   		</tr>
   		
   		<c:forEach var="p" items="${list}">
   			<tr>
	   			<td>${p.name }</td>
	   			<td>${p.description }</td>
	   			<td>
	   				<a href="#">删除</a>
	   				<a href="#">修改</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
   	
   	
  </body>
</html>
