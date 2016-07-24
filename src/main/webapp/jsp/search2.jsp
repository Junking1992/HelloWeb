<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.web.service.ListEntry"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<ListEntry> list = (ArrayList<ListEntry>)session.getAttribute("list");
	int all = session.getAttribute("all") == null ? 0 : (Integer)session.getAttribute("all");
	int currentPage = session.getAttribute("currentPage") == null ? 0 : (Integer)session.getAttribute("currentPage");
	String key = session.getAttribute("key") == null ? "" : (String)session.getAttribute("key");
	int pages = (int)Math.ceil((double)all/10);
%>
<html>
<head>
<title>WebApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- Web-Fonts -->
<link href='http://fonts.useso.com/css?family=Carter+One' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
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
.data {
/* 	width: 20%; */
/* 	height: 100px; */
}
li{
	float:left; 
	list-style:none;
}
</style>
</head>

<body>
	<!-- 导航 -->
	<%@include file="head.jsp"%>
	<div class="col-md-12 column">
		<%
	if(list != null){
		out.print("当前第"+currentPage+"页，共"+ pages +"页,");
		out.print("<a href='/jun/page?where="+((currentPage-1) < 1 ? 1 : (currentPage-1)) +"'>上一页</a>");
		out.print("<a href='/jun/page?where="+((currentPage+1) > pages ? pages : (currentPage+1)) +"'>下一页</a>");
		out.print("<input type='text' id='go' style='width:30px'/>");
		out.print("<input type='button' value='GO' onclick='jump()'/>");
		out.print("<ul>");
		for(ListEntry entry : list){
			out.print("<li>");
			out.print("<div class='data'>");
			out.print("<a href='"+entry.getUrl()+"' title='"+entry.getTitle()+"' style='font-size:5px;' target='_blank'>"+entry.getTitle()+"</a>");
			out.print("<br/><a href='/jun/goInfo?url="+entry.getUrl()+"' title='"+entry.getTitle()+"' target='_blank'><img src='"+entry.getImages()+"' alt='"+entry.getTitle()+"' height='400px'></a>");
			out.print("</div>");
			out.print("</li>");
		}
		out.print("</ul><br/>");
	}
	%>
	
		
	</div>
</body>
</html>