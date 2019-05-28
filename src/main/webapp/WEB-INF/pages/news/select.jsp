﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../util/meta.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<title>新闻管理</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<input type="hidden" name="id" value="${news.id}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>新闻标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${news.title}" placeholder="" id="title" name="title">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>新闻作者：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${news.author}" placeholder="" id="author" name="author">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>新闻来源：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${news.sources}" placeholder="" id="sources" name="sources">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>新闻类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select name="type" class="select">
						<option value="1">公司新闻</option>
						<option value="2">行业新闻</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>简短描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="description" name="description" cols="" rows="" class="textarea" placeholder="">${news.description}</textarea>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">封面图片：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-thum-container">
					<input type="file" id="start-upload" name="imageFile" >
<!-- 					<button type="button" class="btn btn-default btn-uploadstar radius ml-10" id="upload-btn">选择图片</button> -->
				</div>
			</div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">新闻附件：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-thum-container">
					<input type="file" id="start-upload2" name="affixFile" >
				</div>
			</div>
		</div> -->
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">新闻内容：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<textarea name="content" id="editor" style="width:100%;height:250px;">${news.content}</textarea>
				<script type="text/plain" id="editor" style="width:100%;height:250px;"></script>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="submitbtn" onClick="article_save_submit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i>&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
function removeIframe(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function article_save_submit(){
	var title = $("#title").val();
	var author = $("#author").val();
	var sources = $("#sources").val();
	var description = $("#description").val();
	if (title == "") {
		$("#title").focus();
		return false;
	} else if (author == "") {
		$("#author").focus();
		return false;
	} else if (sources == "") {
		$("#sources").focus();
		return false;
	} else if (description == "") {
		$("#description").focus();
		return false;
	} else {
		//ajax提交表单
		var oMyForm = new FormData($('#form-article-add')[0]);
		$.ajax({
			url:"${pageContext.request.contextPath}${news.id==null?'/news/add.shtml':'/news/update.shtml'}",
            type:"post",
            data:oMyForm,
            contentType : false,// 告诉jQuery不要去设置Content-Type请求头  
            processData : false,// 告诉jQuery不要去处理发送的数据  
            success:function(data){
            	if (data == "success") {
                   parent.window.location.href='${pageContext.request.contextPath}/news/list.html';
                   removeIframe();
                } else {
                	layer.msg('保存失败!', {icon:1,time:3000});
                }
            },
		 	complete: function () {//完成响应
		        $("#submitbtn").removeAttr("disabled");
		    },
		    beforeSend: function () {
		        // 禁用按钮防止重复提交，发送前响应
		    	$("#submitbtn").attr({ disabled: "disabled" });
		    }
		})
	}
}

$(function(){
	
	 //初始化
    var ue = UE.getEditor('editor');
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
        	 return '${pageContext.request.contextPath}/fileUpload.do';
        } else if (action == 'fileUpload.do') {
            return '${pageContext.request.contextPath}/fileUpload.do';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
});
</script>
</body>
</html>