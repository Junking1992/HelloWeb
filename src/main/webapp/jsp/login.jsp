<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String msg = (String) (request.getAttribute("msg") == null ? "" : request.getAttribute("msg"));
	if(session.getAttribute("user") != null){
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Logins</title>
<style type="text/css">
#center {
	margin-right: auto;
	margin-left: auto;
	height: 100px;
	width: 800px;
	margin-top: 200px;
	margin-bottom:250px;
	text-align: center;
}
#rights {
	margin-right: auto;
	margin-left: auto;
	text-align: center;
	font-size: 8pt;
}
#login {
	color: #000;
	background-color: #fff;
	font-size: 8pt;
	font-style: normal;
}

</style>
</head>
<body style="margin: 0px;background: #eee;">
	<div id="center">
		<h1>不良网站!!</h1>
		<form action="/HelloWeb/web/login" method="post">
			<span style="font-size: 8pt;"><%=msg%></span><br/> 
			<input name="userName" /> <input type="password" name="passWord" /> <input id='login'
				type="submit" value="Login" />
		</form>
	</div>
	<div id="rights">
		<span>Copyright © 1992 - 2016 Junking. All Rights Reserved</span>
	</div>
</body>
</html>
