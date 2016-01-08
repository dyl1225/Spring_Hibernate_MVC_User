<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 
  
  <!-- 提交给自己 -->
  <sf:form method="post" modelAttribute="user">
  <table align="center" width="700" border="1">
  <tr>
  <td>用户名：</td><td><sf:input path="username"/><sf:errors path="username"/></td>
  </tr>
  <tr>
  <td>用户密码：</td><td><sf:password path="password"/><sf:errors path="password"/></td>
  </tr>
  <tr>
  <td>用户昵称：</td><td><sf:input path="nickname"/></td>
  </tr>
  <tr>
  <td>用户邮箱：</td><td><sf:input path="email"/><sf:errors path="email"/></td>
  </tr>
  <tr>
  <td>是否为领导：</td>
  <td>
          是：<input type="radio" name="isAdmin" value="${true }"/>
       不是：<input type="radio" name="isAdmin" value="${false }"/>
       <sf:errors path="isAdmin"/>
  </td>
  </tr>
  <tr>
  <th colspan="2">
  <input type="submit" value="用户添加"/>
  </th>
  </tr>
  </table>
  </sf:form>
 
  
  </body>
</html>
