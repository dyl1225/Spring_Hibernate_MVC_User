<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>左侧导航</title>
  </head>
  
  <body>
    
    <a href="${pageContext.request.contextPath }/privilege/getAll" target="main">权限管理</a>
    <br/><br/>
    
    <a href="${pageContext.request.contextPath }/resource/getAll" target="main">资源管理</a>
    <br/><br/>
    
    <a href="${pageContext.request.contextPath }/role/getAll" target="main">角色管理</a>
    <br/><br/>
    
    <a href="${pageContext.request.contextPath }/user1/getAll" target="main">用户管理</a>
    <br/><br/>
    
  </body>
</html>
