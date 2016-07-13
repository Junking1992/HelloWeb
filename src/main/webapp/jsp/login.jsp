<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WebApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<link href='http://fonts.useso.com/css?family=Carter+One' rel='stylesheet' type='text/css'>
	<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
	<link href='http://fonts.useso.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
<!-- //Web-Fonts -->
<style type="text/css">
	.whiteFont{
		color : white;
	}
	.alpha {background-color:rgba(
		0,0,0,0)
	}
	.vertical-center{
	  position: absolute;
	  top: 50%;
	  left: 50%;
	  transform: translate(-50%, -60%);
	} 
	.h1Font{
		font-family: 'Carter One', cursive;
		font-size:40px;
	}
	.pFont{
		font-weight:600;
	}
	.junMsg{
		height:30px;
		padding-top:5px;
		text-align:center;
	}
	.bodyBackground{
		background: url(../images/bg.jpg) no-repeat center center; 
		background-attachment: fixed;
		background-size:cover;
	}
</style>
</head>

<body class="bodyBackground">
	<div class="container vertical-center" >
		<div class="row">
			<div class="col-xs-4 col-md-offset-4 " style="margin-top: 50px">
				<form method="post" action="/HelloWeb/web/login">
					<div class="page-header">
					  <h1 class="text-center whiteFont h1Font" >Sign in to System</h1>
					</div>
						<div class="panel-body ">
					   		<p class="text-left whiteFont pFont">帐号</p>
					   		<div class="input-group col-sm-12">
								<input type="text" class="form-control alpha whiteFont" placeholder="Username" name="userName" 

value="${userName}">
							</div><br/>
					   		<p class="text-left whiteFont pFont">密码</p>
							<div class="input-group col-sm-12">
								<input type="password" class="form-control alpha whiteFont" placeholder="Password" name="passWord">
							</div>
							<div class="junMsg">
								<span class="whiteFont">${msg}</span>
							</div>
							<button type="submit" class="btn btn-default btn-block pFont">
						     	 登录
						    </button>
						</div>
				</form>
				<address style="margin-top:70px" class="text-center whiteFont">
				  <strong>Copyright © 1992 - 2016 Junking. All Rights Reserved</strong><br>
				</address>
			</div>
		</div>
	</div>
</body>
</html>