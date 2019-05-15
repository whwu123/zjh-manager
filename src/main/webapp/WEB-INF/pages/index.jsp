<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="util/meta.jsp"></jsp:include>
<title>后台管理</title>
</head>
<body>
<jsp:include page="util/header.jsp"></jsp:include>
<jsp:include page="util/menu.jsp"></jsp:include>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont"></i> <a href="/" class="maincolor">首页</a> 
		<span class="c-999 en">&gt;</span>
		<span class="c-666">我的桌面</span> 
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<p class="f-20 text-success">welcome to 长沙中竞恒招投标代理有限公司 
				<span class="f-14">v1.0</span>
			</p>
<!-- 			<p>登录次数：18 </p> -->
<!-- 			<p>上次登录IP：222.35.131.79.1  上次登录时间：2014-6-14 11:19:55</p> -->
			<table class="table table-border table-bordered table-bg" style="display: none">
				<thead>
					<tr>
						<th colspan="7" scope="col">信息统计</th>
			</tr>
					<tr class="text-c">
						<th>统计</th>
						<th>资讯库</th>
						<th>图片库</th>
						<th>产品库</th>
						<th>用户</th>
						<th>管理员</th>
			</tr>
		</thead>
				<tbody>
					<tr class="text-c">
						<td>总数</td>
						<td>92</td>
						<td>9</td>
						<td>0</td>
						<td>8</td>
						<td>20</td>
			</tr>
					<tr class="text-c">
						<td>今日</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
			</tr>
					<tr class="text-c">
						<td>昨日</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
			</tr>
					<tr class="text-c">
						<td>本周</td>
						<td>2</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
			</tr>
					<tr class="text-c">
						<td>本月</td>
						<td>2</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
			</tr>
		</tbody>
	</table>
			<table class="table table-border table-bordered table-bg mt-20">
				<thead>
					<tr>
						<th colspan="2" scope="col">服务器信息</th>
			</tr>
		</thead>
				<tbody>
					<tr>
						<th width="30%">服务器计算机名</th>
						<td><span id="lbServerName">instance-e0wv0zts</span></td>
			</tr>
					<tr>
						<td>服务器IP地址</td>
						<td>182.61.54.100</td>
			</tr>
					<tr>
						<td>服务器域名</td>
						<td>www.zjhbidding.com</td>
			</tr>
					<tr>
						<td>服务器端口 </td>
						<td>8082</td>
			</tr>
				<!-- 	<tr>
						<td>服务器IIS版本 </td>
						<td>Microsoft-IIS/6.0</td>
			</tr>
					<tr>
						<td>本文件所在文件夹 </td>
						<td>D:\WebSite\HanXiPuTai.com\XinYiCMS.Web\</td>
			</tr> -->
					 <tr>
						<td>服务器操作系统 </td>
						<td>CentOS / 7 </td>
			</tr> 
					<tr>
				<!--		<td>系统所在文件夹 </td>
						<td>/zjh/apache-tomcat-7.0.57</td>
			</tr>-->
					<tr>
						<td>服务器的语言种类 </td>
						<td>English (People's Republic of English)</td>
			</tr>
					<tr>
						<td>CPU 总数 </td>
						<td>1</td>
			</tr>
					<tr>
						<td>虚拟内存 </td>
						<td>1G</td>
			</tr>

					<tr>
						<td>当前系统用户名 </td>
						<td>Admin</td>
			</tr>
		</tbody>
	</table>
</article>
</div>
</section>
<jsp:include page="util/footer.jsp"></jsp:include>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>