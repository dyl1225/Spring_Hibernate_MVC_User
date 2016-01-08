<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加资源界面</title>
  </head>
  
  <body>
   <sf:form action="${pageContext.request.contextPath }/resource/add" method="post" modelAttribute="resource">
   	<table>
   		<tr>
   			<td>资源URI</td>
   			<td>
   				<sf:input path="uri"/><sf:errors path="uri"></sf:errors>
   			</td>
   		</tr>
   		<tr>
   			<td>资源描述</td>
   			<td>
				<sf:textarea path="description" rows="5" cols="50"/>
				<sf:errors path="description"></sf:errors>
   			</td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
				<input type="submit" value="添加资源">
   			</td>
   		</tr>
   	</table>
   	</sf:form>
  </body>
</html>
