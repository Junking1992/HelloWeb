<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%
	List<String> files = (List<String>) request.getAttribute("files");
	List<String> crumbs = (List<String>) request.getAttribute("crumbs");
	String path = (String) request.getAttribute("path");
	String msg = (String) request.getAttribute("msg");
	String flag = (String) request.getAttribute("flag");
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
<!-- <link href='http://fonts.useso.com/css?family=Carter+One' rel='stylesheet' type='text/css'> -->
<!-- <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'> -->
<!-- <link href='http://fonts.useso.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'> -->
<!-- //Web-Fonts -->
<style type="text/css">
.titleFont {
/* 	font-family: 'Carter One', cursive; */
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
				<ol class="breadcrumb">
					<%
						for (String crumb : crumbs) {
							out.println(crumb);
						}
					%>
				</ol>
				<div id="success" class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>成功：</strong><%=msg %>
				</div>
				<div id="danger" class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>警告：</strong><%=msg %>
				</div>
				<ul class="list-group">
					<%
						for (String file : files) {
					%>
					<li class="list-group-item"><%=file%></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<form role="form" id="deleteForm" method="post" action="/web/deleteFile">
					<div class="modal-body">
						<input type="text" class="form-control" id="deleteKey" name="deleteKey" placeholder="请输入口令进行删除操作" onfocus="hiddenError()"> <input type="hidden" class="form-control" id="path" name="path"> <input type="hidden" class="form-control" id="fileName" name="fileName">
					</div>
					<div class="modal-footer">
						<span style="float:left; color:red; display:none;" id="error">请输入口令！</span>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" onclick="checkDeleteKey()">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="imagePage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h5 class="modal-title" id="imageViewTitle"></h5>
							</div>
							<div class="modal-body">
								<img src="" class="img-thumbnail img-responsive center-block" alt="Cinque Terre" id="imageView" onclick="nextImage()">
							</div>
						</div>
					</div>
				</div>
</body>
<script type="text/javascript">
	var flag = <%=flag%>;
	var path = "<%=path%>";
	var nextLine;
	if(flag != null){
		if(flag){
			$("#danger").alert("close");
		}else{
			$("#success").alert("close");
		}
	}else{
		$(".alert").alert('close'); 
	}
	
	function getFileName(button) {
		var fileName = button.parentNode.firstChild.innerHTML;
		var filePath = path.substr(0,1)+":"+path.substr(1)+"/"+fileName;
		$("#myModalLabel").html("确定要删除:" + filePath + "?");
		$("#path").val(encodeURI(path));
		$("#fileName").val(fileName);
		$("#deleteKey").val("");
	}
	
	function checkDeleteKey(){
		if($("#deleteKey").val() != ""){
			$("#deleteForm").submit();
		}else{
			$("#error").css("display","block");
		}
	}
	
	function hiddenError(){
		$("#error").css("display","none");
	}
	
	function showImage(image){
		//下一行元素
		nextLine = image.parentNode.nextSibling.nextSibling.lastChild;
		var imageName = image.parentNode.firstChild.innerHTML;
		$("#imageViewTitle").html(imageName);
		var imageUrl = "/web/showImage/" + path + "/" + imageName;
		$("#imageView").attr("src",imageUrl);
	}
	
	function nextImage(){
		showImage(nextLine);
	}
</script>
</html>
