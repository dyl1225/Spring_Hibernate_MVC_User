<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath() %>/login">


    <table align="center" border="1" width="500">
    <tr>
	<td>用户名:</td><td><input type="text" name="username"/></td>
	</tr>
	<tr>
	<td>用户密码:</td><td><input type="password" name="password"/></td>
	</tr>
	<tr>
	<td>有效期:</td>
	<td>
	一分钟：<input type="radio" name="time" value="${1*60 }"/>
	五分钟：<input type="radio" name="time" value="${5*60 }"/>
	十分钟：<input type="radio" name="time" value="${10*60 }"/>
	</td>
	</tr>
	<tr>
	<th colspan="2" >
	<input type="submit" value="用户登录"/>
	</th>
	</tr>
	</table>
</form>
</body>
</html>