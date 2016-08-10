<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WebApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
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

.userMmargin {
	margin-right: 20px;
}

.leftMenu .panel-heading {
	font-size: 14px;
	padding-left: 20px;
	height: 36px;
	line-height: 36px;
	position: relative;
	cursor: pointer;
}
/*转成手形图标*/
.leftMenu .panel-heading span {
	position: absolute;
	right: 10px;
	top: 12px;
}
</style>
</head>

<body>
	<!-- 导航 -->
	<%@include file="head.jsp"%>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="col-md-2 column">
					<div class="panel-group" id="panel-640554">
						<div class="panel leftMenu">
							<div class="panel panel-default">
								<div class="panel-heading" data-toggle="collapse"
									data-target="#panel-1">
									<h4 class="panel-title">
										功能一 <span class="glyphicon glyphicon-chevron-up right"></span>
									</h4>
								</div>
								<div id="panel-1" class="panel-collapse collapse">
									<div class="list-group">
										<a href="/web/search" class="list-group-item"> S2 </a> <a
											href="/web/files" class="list-group-item">FileSystem</a>
										<a href="/web/video" class="list-group-item">video</a> <a
											href="#" class="list-group-item">Porta ac consectetur ac</a>
										<a href="#" class="list-group-item">Vestibulum at eros</a>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading" data-toggle="collapse"
									data-target="#panel-2">
									<h4 class="panel-title">
										功能二<span class="glyphicon glyphicon-chevron-up right"></span>
									</h4>
								</div>
								<div id="panel-2" class="panel-collapse collapse">
									<div class="list-group">
										<a href="#" class="list-group-item"> Cras justo odio </a> <a
											href="#" class="list-group-item">Dapibus ac facilisis in</a>
										<a href="#" class="list-group-item">Morbi leo risus</a> <a
											href="#" class="list-group-item">Porta ac consectetur ac</a>
										<a href="#" class="list-group-item">Vestibulum at eros</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-10 column">
					<div class="jumbotron">
						<h1>Hello, world!</h1>
						<p>This is a template for a simple marketing or informational
							website. It includes a large callout called the hero unit and
							three supporting pieces of content. Use it as a starting point to
							create something more unique.</p>
						<p>
							<a class="btn btn-primary btn-large" href="#">Learn more</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$(".panel-heading").click(function(e) {
				/*切换折叠指示图标*/
				$(this).find("span").toggleClass("glyphicon-chevron-down");
				$(this).find("span").toggleClass("glyphicon-chevron-up");
			});
		});
	</script>
</body>
</html>