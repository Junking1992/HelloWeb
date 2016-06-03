<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
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
#body01{
	border:4px;
	border-style: none;
}
</style>
</head>
<%
	com.junking.model.User user = (com.junking.model.User)session.getAttribute("user"); 
	if(user!=null){
%>
<body style="margin: 0px;">
	<div id="titlle">
		<span style="font-size: 40pt;margin-left: 100px">Web Index!</sapn>
		<a style="font-size: 10pt; margin-left: 750px; color: #000" href="/HelloWeb/web/cancel"><%=user.getName() %>,退出</a>
	</div>
	<div id="body01">
		<input type="text" name='userName'/>
		<input type="text" name='passWord'/>
		<input type="button" value="启动"/>
	</div>
</body>
<%
	}else{
%>
<script type="text/javascript" charset="UTF-8">
	alert("页面过期，请重新登录");
	window.top.location.href="http://localhost:8080/HelloWeb/";
</script>
<%
	}
%>

</html>
