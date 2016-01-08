<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  
  <center>
  
  <h1>用户列表页面</h1>
  aaaaa-------------
  <table align="center" width="700" border="1">
  <tr>
  <td>用户标识</td><td>用户名</td><td>用户密码</td><td>用户昵称</td><td>用户邮箱</td><td>是否是领导</td><td>操作</td>
  </tr>
  <c:if test="${pager.total lt 0 }">
  <tr>
  <td colspan="7">
     目前还没有用户数据
  </td>
  </tr>
  </c:if>
  <c:if test="${pager.total gt 0 }">
  
  <c:forEach items="${pager.datas }" var="u">
  <c:if test="${u.isAdmin==true }">
  <tr>
  <td>${u.id }</td><td>${u.username }</td>
  <td>${u.password }</td>
  <td><a href="user/${u.id }">${u.nickname }</a></td>
  <td>${u.email }</td>
  <td>${u.isAdmin }</td>
  <td><a href="user/${u.id }/update">更新</a>&nbsp;<a href="user/${u.id }/delete">删除</a></td>
  </tr>
  </c:if>
  <c:if test="${u.isAdmin==false }">
  <tr>
  <td>${u.id }</td><td>${u.username }</td>
  <td>${u.password }</td>
  <td><a href="user/${u.id }">${u.nickname }</a></td>
  <td>${u.email }</td>
  <td>${u.isAdmin }</td>
  <td><a href="user/${u.id }/update">更新</a>&nbsp;<a href="user/${u.id }/delete">删除</a></td>
  </tr>
  </c:if>
  </c:forEach>
  
  <tr>
  <th colspan="7">
  	<jsp:include page="/inc/pager.jsp">
  	<jsp:param value="user/users" name="url"/>
  	<jsp:param value="${pager.total }" name="items"/>
  	</jsp:include>
  </th>
  </tr>
  
  </c:if>
 
  
  </table>
  
  </center>
  </body>
</html>
