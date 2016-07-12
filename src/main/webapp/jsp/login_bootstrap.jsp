<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap 模板</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-md-offset-4"
				style="margin-top: 100px">
				<form>
					<div class="panel panel-default">
					   <div class="panel-body" style="background-color: #eee">
					   		<p class="text-left">帐号</p>
					   		<div class="input-group col-sm-12">
								<input type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon2">
							</div><br/>
					   		<p class="text-left">密码</p>
							<div class="input-group col-sm-12">
								<input type="password" class="form-control" placeholder="Password" aria-describedby="sizing-addon2">
							</div><br/>
							<button type="button" class="btn btn-default btn-block">
						     	 登录
						    </button>
					   </div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>