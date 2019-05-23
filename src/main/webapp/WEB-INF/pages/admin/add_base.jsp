<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="../util/meta.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<title>链接管理</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<input type="hidden" name="fId" value="${items.fId}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${items.fTitle}" placeholder="" id="fTitle" name="fTitle">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>URL：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${items.fUrl}" placeholder="" id="fUrl" name="fUrl">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-9">
				
				<span class="select-box">
					<select name="fStatus" class="select">
					
						<option value="1" <c:if test="${items.fStatus ==1 }">selected="selected"</c:if> >启用</option>
						<option value="0" <c:if test="${items.fStatus ==0 }">selected="selected"</c:if> >禁用</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select name="fType" class="select">
						<option value="1" selected="selected">友情链接</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">简短描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="fDescrition" name="fDescrition" cols="" rows="" class="textarea" placeholder="">${items.fDescrition}</textarea>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i>&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<jsp:include page="../util/footer.jsp"></jsp:include>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
function removeIframe(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function article_save_submit(){
	var fTitle = $("#fTitle").val();
	var fUrl = $("#fUrl").val();
	var fStatus = $("#fStatus").val();
	if (fTitle == "") {
		$("#fTitle").focus();
		return false;
	} else if (fUrl == "") {
		$("#fUrl").focus();
		return false;
	} else {
		//ajax提交表单
		var oMyForm = new FormData($('#form-article-add')[0]);
		$.ajax({
			url:"${pageContext.request.contextPath}${items.fId==null?'/admin/add.html':'/admin/update.html'}",
            type:"post",
            data:oMyForm,
            contentType : false,// 告诉jQuery不要去设置Content-Type请求头  
            processData : false,// 告诉jQuery不要去处理发送的数据  
            success:function(data){
            	if (data == "success") {
                    parent.window.location.href='${pageContext.request.contextPath}/admin/system-base.html';
                    removeIframe();
                } else {
                	layer.msg('保存失败!', {icon:1,time:3000});
                }
            }
		})
	}
}

</script>
</body>
</html>