<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--_header 作为公共模版分离出去-->
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="${pageContext.request.contextPath}/index.html">中竞恒招投标代理</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">中竞恒</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="news_add('添加新闻','${pageContext.request.contextPath}/news/select.html')"><i class="Hui-iconfont">&#xe616;</i> 新闻</a></li>
							<li><a href="javascript:;" onclick="case_add('添加案例','${pageContext.request.contextPath}/case/select.html')"><i class="Hui-iconfont">&#xe613;</i> 案例</a></li>
							<li><a href="javascript:;" onclick="affix_add('添加附件','${pageContext.request.contextPath}/affix/add_affix.html')"><i class="Hui-iconfont">&#xe620;</i> 附件</a></li>
						</ul>
			</li>
		</ul>
	</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>${sessionScope.role}</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${sessionScope.userName} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onClick="myselfinfo('管理员信息1234')">个人信息</a></li>
<!-- 							<li><a href="#">切换账户</a></li> -->
							<li><a id="logout" onclick="logout();">退出</a></li>
				</ul>
			</li>
					<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
				</ul>
			</li>
		</ul>
	</nav>
</div>
</div>
</header>
<script type="text/javascript">


function logout(){
	$.ajax({
			type:'post',
			url:'logout.do', 
			success:function(data){  
        	   if(data=="success"){ 
                   location.href = 'login.jsp';//进入后台首页  
                }else{  
                   layer.msg('注销失败!！',{icon:2,time:1500});
               }  
        	}
		})

}



/*新闻-添加*/
function news_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*案例-添加*/
function case_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*附件-添加*/
function affix_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
</script>
<!--/_header 作为公共模版分离出去-->