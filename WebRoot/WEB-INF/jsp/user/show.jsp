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
    
    <title>用户${user.nickname }详细信息</title>
    
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
 
  

  <table align="center" width="700" border="1">
  <tr>
  <td>用户标识：</td><td>${user.id }</td>
  </tr>
  <tr>
  <td>用户名：</td><td>${user.username }</td>
  </tr>
  <tr>
  <td>用户密码：</td><td>${user.password }</td>
  </tr>
  <tr>
  <td>用户昵称：</td><td>${user.nickname }</td>
  </tr>
  <tr>
  <td>用户邮箱：</td><td>${user.email }</td>
  </tr>
  <tr>
  <th colspan="2">
     <a href="user/users">返回用户列表</a> 
  </th>
  </tr>
  </table>
 
 
  
  </body>
</html>
