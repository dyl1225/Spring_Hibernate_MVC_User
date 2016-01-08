<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户界面</title>
  </head>
  
  <body>
   	<sf:form action="${pageContext.request.contextPath }/user1/add" method="post" modelAttribute="user1">
   	<table>
   		<tr>
   			<td>用户名</td>
   			<td>
   				<sf:input path="username"/><sf:errors path="username"></sf:errors>
   			</td>
   		</tr>
   		<tr>
   			<td>用户密码</td>
   			<td>
   				<sf:input path="password"/><sf:errors path="password"></sf:errors>
   			</td>
   		</tr>
   		<tr>
   			<td>用户昵称</td>
   			<td>
   				<sf:input path="nickname"/><sf:errors path="nickname"></sf:errors>
   			</td>
   		</tr>
   		<tr>
   			<td>用户邮箱</td>
   			<td>
				<sf:input path="email"/><sf:errors path="email"></sf:errors>
   			</td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
				<input type="submit" value="添加用户">
   			</td>
   		</tr>
   	</table>
   	</sf:form>
  </body>
</html>
