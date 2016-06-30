<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.junking.model.User" %>
<%
	User user = session.getAttribute("user") == null ? new User() : (User)session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>WebApp</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.css" />
	<script src="../js/jquery-1.12.4.js"></script>
	<script src="../js/jquery.mobile-1.4.5.js"></script>
</head>

<body>
	<!-- Page1 -->
	<div data-role="page" id="index" data-theme="b">
		<!-- header -->
		<div data-role="header" data-position="fixed">
			<h1>首页</h1>
		</div>
		<!-- content -->
		<div role="main" class="ui-content">
			<p ><%="欢迎:" + user.getName() %></p>
			<a href="exit">退出</a>
		</div>
		<!-- footer -->
		<div data-role="footer" data-position="fixed">
		</div>
	</div>
</body>
</html>