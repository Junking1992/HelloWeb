<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String msg = (String) (request.getAttribute("msg") == null ? "" : request.getAttribute("msg"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>WebApp</title>
<meta name="viewport" content="width=50%, initial-scale=1">
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css" />
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery.mobile-1.4.5.js"></script>
</head>

<body>
	<!-- Page1 -->
	<div data-role="page" id="index" data-theme="b">
		<!-- header -->
		<div data-role="header" data-position="fixed"></div>
		<!-- content -->
		<div role="main" class="ui-content">
			<form method="post" action="/HelloWeb/web/login">
				<div data-role="fieldcontain" >
					<input type="text" name="userName" id="userName" placeholder="帐号"> 
					<input type="text" name="passWord" id="passWord" placeholder="密码">
				</div>
				<input type="submit" data-inline="true" data-transition="flip" value="提交">
			</form>
		</div>
		<!-- footer -->
		<div data-role="footer" data-position="fixed"></div>
	</div>
</body>
</html>