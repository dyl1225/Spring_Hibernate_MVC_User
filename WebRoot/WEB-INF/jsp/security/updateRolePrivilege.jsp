<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新角色的权限</title>
  </head>
  
  <body>
    
    <table frame="border" width="40%">
    	<tr>
    		<td>角名</td>
    		<td>${role.name }</td>
    	</tr>
    	
    	<tr>
    		<td>角色描述</td>
    		<td>${role.description }</td>
    	</tr>
    	
    	<tr>
    		<td>角色原有权限</td>
    		<td>
    			<c:forEach var="p" items="${role.privileges}">
    				${p.name }<br/>
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>需授予的权限</td>
    		<td>
    			<!-- 当下面表单提交时，会给服务器带去：角色id和要授予的权限id  -->
    			<form method="post">
	    			<input type="hidden" name="roleid" value="${role.id }">
	    			<c:forEach var="p" items="${list}">
	    				<input type="checkbox" name="pid" value=${p.id }>${p.name }<br/>
	    			</c:forEach>
	   				<input type="submit" value="更新权限">
   				</form>
    		</td>
    	</tr>
    
    </table>
    
    
  </body>
</html>

