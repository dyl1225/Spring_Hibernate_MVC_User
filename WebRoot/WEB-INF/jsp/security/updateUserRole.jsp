<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新用户的角色</title>
  </head>
  
  <body>
    
    <table frame="border" width="40%">
    	<tr>
    		<td>用户名</td>
    		<td>${user1.username }</td>
    	</tr>
    	
    	<tr>
    		<td>用户昵称</td>
    		<td>${user1.nickname }</td>
    	</tr>
    	
    	<tr>
    		<td>用户原有角色</td>
    		<td>
    			<c:forEach var="role" items="${user1.roles}">
    				${role.name }<br/>
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>需授予的角色</td>
    		<td>
    			<!-- 当下面表单提交时，会给服务器带去：用户id和要授予的角色id  -->
    			<form  method="post">
	    			<input type="hidden" name="userid" value="${user1.id }">
	    			<c:forEach var="r" items="${list}">
	    				<input type="checkbox" name="rid" value=${r.id }>${r.name }<br/>
	    			</c:forEach>
	   				<input type="submit" value="更新角色">
   				</form>
    		</td>
    	</tr>
    
    </table>
    
    
  </body>
</html>

