<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>资源列表</title>
  </head>
  
  <body style="text-align: center;">
   	<br/>	<br/>
   	
   	<table width="80%" align="center">
   		<tr>
   			<td></td>
   			<td></td>
   			<td align="right">
   				<a href="${pageContext.request.contextPath }/resource/add">添加资源</a>
   			</td>
   		</tr>
   	</table>
   	<table width="80%" frame="border" border="1" align="center" bordercolor="blue">
   		<tr>
   			<td>资源uri</td>
   			<td>控制资源的权限</td>
   			<td>资源描述</td>
   			<td>操作</td>
   		</tr>
   		
   		<c:forEach var="r" items="${list}">
   			<tr>
	   			<td>${r.uri }</td>
	   			<td>${r.privilege.name }</td>
	   			<td>${r.description }</td>
	   			<td>
	   				<a href="${pageContext.request.contextPath }/resource/${r.id }/update">修改资源的权限</a>
	   				<a href="${pageContext.request.contextPath }/resource/${r.id }/delete">删除</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
   	
  </body>
</html>
