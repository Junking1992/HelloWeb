<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>WebApp</title>
</head>

<body>
	<form method="post" action="/HelloWeb/web/login">
		帐号：<input type="text" name="userName" value="${userName}"><font color="red">${msg}</font><br/> 
		密码：<input type="password" name="passWord"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>