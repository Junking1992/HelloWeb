<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.junking.service.ListEntry"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	List<ListEntry> list = (ArrayList<ListEntry>)session.getAttribute("list");
	int all = session.getAttribute("all") == null ? 0 : (Integer)session.getAttribute("all");
	int currentPage = session.getAttribute("currentPage") == null ? 0 : (Integer)session.getAttribute("currentPage");
	String key = session.getAttribute("key") == null ? "" : (String)session.getAttribute("key");
	int pages = (int)Math.ceil((double)all/10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>WebApp</title>
<style>
	li{float:left; list-style:none;}
}
</style>

<script type="text/javascript">
	function jump(){
		var go = document.getElementById("go").value;
		var currentPage = <%=currentPage %>;
		if(go != ""){
	 		window.location.href="/HelloWeb/jun/page?where="+go;
		}
	}
</script>
</head>

<body>
	<form method="post" action="/HelloWeb/jun/search">
		<input type="text" name="key" id="key" value="<%=key %>"> 
		<input type="submit" data-inline="true" value="搜索">
	</form>
	<%
	if(list != null){
		out.print("当前第"+currentPage+"页，共"+ pages +"页,");
		out.print("<a href='/HelloWeb/jun/page?where="+((currentPage-1) < 1 ? 1 : (currentPage-1)) +"'>上一页</a>");
		out.print("<a href='/HelloWeb/jun/page?where="+((currentPage+1) > pages ? pages : (currentPage+1)) +"'>下一页</a>");
		out.print("<input type='text' id='go' style='width:30px'/>");
		out.print("<input type='button' value='GO' onclick='jump()'/>");
		out.print("<ul>");
		for(ListEntry entry : list){
			out.print("<li>");
			out.print("<a href='"+entry.getUrl()+"' title='"+entry.getTitle()+"' style='font-size:5px;' target='_blank'>"+entry.getTitle()+"</a>");
			out.print("<br/><a href='/HelloWeb/jun/goInfo?url="+entry.getUrl()+"' title='"+entry.getTitle()+"' target='_blank'><img src='"+entry.getImages()+"' alt='"+entry.getTitle()+"' height='400px'></a>");
			out.print("</li>");
		}
		out.print("</ul><br/>");
	}
	%>
</body>
</html>