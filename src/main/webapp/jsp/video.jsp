<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String videoPath = (String) request.getAttribute("videoPath"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WebApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</style>
</head>

<body>
	<!-- 导航 -->
	<%@include file="head.jsp"%>
	<div class="container">
		<div id="a1"></div>
	</div>
</body>
<script type="text/javascript" src="/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
// 	var flashvars = {
// 		f : '',
// 		c : 0,
// 		p : 1,
// 		v : 50
// 	};
// 	var params = {
// 		bgcolor : '#FFF',
// 		allowFullScreen : true,
// 		allowScriptAccess : 'always',
// 		wmode : 'transparent'
// 	};
<%-- 	var video = [ '/<%=videoPath%>->video/mp4' ]; --%>
// 	CKobject.embed('/ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '100%',
// 			'100%', true, flashvars, video, params);
    var flashvars={
        f:'/<%=videoPath%>',
        c:0,
        p:1,
        v:20,
        wh:'16:9',
        e:1,
        h:1,
        n:'asdasdasdasd'
    };
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    var video=['/<%=videoPath%>->video/mp4'];
    CKobject.embed('/ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',true,flashvars,video,params);
</script>
</html>