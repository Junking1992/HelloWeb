<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 导航 -->
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