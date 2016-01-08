<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加角色界面</title>
  </head>
  
  <body>
   	<sf:form action="${pageContext.request.contextPath }/role/add" method="post" modelAttribute="role">
   	<table>
   		<tr>
   			<td>角色名称</td>
   			<td>
   				<sf:input path="name"/><sf:errors path="name"></sf:errors>
   			</td>
   		</tr>
   		<tr>
   			<td>角色描述</td>
   			<td>
				<sf:textarea path="description" rows="5" cols="50"/>
				<sf:errors path="description"></sf:errors>
   			</td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
				<input type="submit" value="添加角色">
   			</td>
   		</tr>
   	</table>
   	</sf:form>
  </body>
</html>
