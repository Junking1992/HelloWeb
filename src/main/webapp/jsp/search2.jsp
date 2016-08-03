<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.web.model.ListEntry"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<ListEntry> datas = (ArrayList<ListEntry>) request.getAttribute("datas");
	int allCount = request.getAttribute("allCount") == null ? 0 : (Integer) request.getAttribute("allCount");
	int currentPage = request.getAttribute("currentPage") == null ? 0 : (Integer) request.getAttribute("currentPage");
	String key = request.getAttribute("key") == null ? "" : (String) request.getAttribute("key");
	int pages = (int) Math.ceil((double) allCount / 10);
%>
<html>
<head>
<title>WebApp</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
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

.Absolute-Center.is-Image {
	height: auto;
}

.Absolute-Center.is-Image img {
	width: 100%;
	height: auto;
}

.ellipsis{
	white-space:nowrap;
	text-overflow: ellipsis;
	overflow:hidden;
}

img {
	max-height: 100%;
	width: auto;
}
</style>
</head>

<body>
	<!-- 导航 -->
	<%@include file="head.jsp"%>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<%
				for(ListEntry data : datas){
				%>
				<div class="col-md-3 column">
					<div class="panel panel-default">
						<div class="panel-heading"><h5 class="panel-title ellipsis" dir="ltr"><%=data.getTitle() %></h5></div>
						<div class="panel-body" style="height: 200px;">
							<img src="/web/showImage/E/20160714 桂林湿身游/DSC_8812.JPG"
								class="img-thumbnail center-block" alt="Cinque Terre"
								id="imageView">
						</div>
						<div class="panel-footer">
							<a href="<%=data.getUrl() %>" target="_blank" class="btn btn-success btn-xs">成功</a>
							<a href="<%=data.getSource() %>" target="_blank" class="btn btn-danger btn-xs">源</a>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<%if(allCount>0){ %>
			<nav style="text-align: center">
			<ul class="pagination">
				<li><a href="#">Prev</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">Next</a></li>
			</ul>
			</nav>
			<%} %>
		</div>
	</div>
</body>
</html>