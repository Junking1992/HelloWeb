<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WebApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- Web-Fonts -->
<link href='http://fonts.useso.com/css?family=Carter+One'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Cabin:400,500,600,700'
	rel='stylesheet' type='text/css'>
<!-- //Web-Fonts -->
<style type="text/css">
.titleFont {
	font-family: 'Carter One', cursive;
	font-size: 30px;
}
.userMmargin{
	margin-right: 20px;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand titleFont" href="/web/index">System</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/web/index">首页</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a  href="#" class="dropdown-toggle glyphicon glyphicon-user " data-toggle="dropdown"></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header">姓名: ${user.name}</li> 
						<li class="dropdown-header">Ip地址: ${user.ip}</li>
						<li class="dropdown-header">登录地址：${user.address}---${user.location}</li>
						<li class="dropdown-header">登录时间: ${user.loginTime}</li>
						<li class="divider"></li>
						<li >
							<a  href="/web/loginOut" style="color:red; font-weight:600" class="text-center">登出</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>