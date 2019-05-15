<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script><![endif]-->
<title>中竞恒</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header" style="background: #426374;line-height: 60px;font-size: 30px;color: #EAEDF3;">&nbsp;&nbsp;中竞恒招投标 后台管理系统</div>
<div class="loginWraper">
	<div id="loginform" class="loginBox">
		<form class="form form-horizontal" action="login.do" method="post">
			<div class="row cl">
				<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
				<div class="formControls col-xs-8">
					<input id="userName" name="userName" type="text" placeholder="账户" class="input-text size-L">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
				<div class="formControls col-xs-8">
					<input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
				</div>
			</div>
<!-- 			<div class="row cl"> -->
<!-- 				<div class="formControls col-xs-8 col-xs-offset-3"> -->
<!-- 					<input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;"> -->
<!-- 					<img src="images/VerifyCode.aspx.png"> -->
<!-- 					<a id="kanbuq" href="javascript:;">看不清，换一张</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="row cl"> -->
<!-- 				<div class="formControls col-xs-8 col-xs-offset-3"> -->
<!-- 					<label for="online"> -->
<!-- 						<input type="checkbox" name="online" id="online" value=""> -->
<!-- 						使我保持登录状态</label> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="row cl">
				<div class="formControls col-xs-8 col-xs-offset-3">
					<input id="login" name="login" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
					<input name="reset" type="reset" class="btn btn-default radius size-L" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
				</div>
			</div>
		</form>
	</div>
</div>
<div class="footer" style="font-family: '微软雅黑;'">Copyright 长沙中竞恒招投标代理有限公司</div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script> 

<script>
var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef"+ "ghijklmnopqrstuv" + "wxyz0123456789+/" + "=";
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
  
  
  $("#login").click(function(){
  	var userName = $("#userName").val();
  	var password = $("#password").val();
  	
  	var sendUserName = encode64(userName);
  	var serdPassword = encode64(password);
  
  	$.ajax({
  	 	url:'login.do',  
        type:'post',  
        data:{userName:sendUserName,password:serdPassword},  
        success:function(data){  
        	   if(data=="success"){ 
        	   	   layer.msg('登录成功！正在进入首页!请稍后~~~',{icon:1,time:1000});
                   location.href = 'index.do';//进入后台首页  
                }else{  
                   layer.msg('登录失败!用户名或密码错误！',{icon:2,time:1500});
                   $("#password").val("");
                   $("#password").focus();
               }  
        }
  	
  	});
  
  });
  
})();



	function encode64(input) {
		var output = "";
		var chr1, chr2, chr3 = "";
		var enc1, enc2, enc3, enc4 = "";
		var i = 0;
		do {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
					+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
			chr1 = chr2 = chr3 = "";
			enc1 = enc2 = enc3 = enc4 = "";
		} while (i < input.length);
		return output;
	}
</script>
</body>
</html>