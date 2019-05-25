<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>这个.. 页面没有找到！！！_SJ5D.COM</title>
		<link href="<%=basePath %>static/404.css" rel="stylesheet" type="text/css" />
		<link rel="Bookmark" href="favicon.ico" >
		<link rel="Shortcut Icon" href="favicon.ico" />
	</head>
	<body>
		<div id="mother">
			<div id="errorBox">
				<div id="errorText">
					<h1>Sorry..页面没有找到！</h1>
					<p>
						似乎你所寻找的网页已移动或丢失了。
						<p>或者也许你只是键入错误了一些东西。</p>
						请不要担心，这没事。如果该资源对你很重要，请与管理员联系。
					</p>

					<p>
						火星不太安全，我可以免费送你回地球
					</p>
				</div>
				<a href="<%=basePath %>index.do" title="返回SJ5D首页">
					<div class="link" id="home"></div>
				</a>
				<a href="<%=basePath %>index.do" title="联系管理员">
					<div class="link" id="contact"></div>

				</a>
			</div>
		</div>
	</body>
</html>