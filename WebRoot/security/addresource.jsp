<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加资源界面</title>
  </head>
  
  <body>
   	<form action="${pageContext.request.contextPath }/servlet/ResourceServlet?method=add" method="post">
   	<table>
   		<tr>
   			<td>资源URI</td>
   			<td>
   				<input type="text" name="uri">
   			</td>
   		</tr>
   		<tr>
   			<td>资源描述</td>
   			<td>
				<textarea rows="5" cols="50" name="description"></textarea>
   			</td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
				<input type="submit" value="添加资源">
   			</td>
   		</tr>
   	</table>
   	</form>
  </body>
</html>
