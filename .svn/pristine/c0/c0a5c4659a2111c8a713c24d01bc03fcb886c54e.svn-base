<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../util/meta.jsp"></jsp:include>
<style>
.fade {opacity: 0;
	-webkit-transition: opacity .15s linear;
	-o-transition: opacity .15s linear;
	transition: opacity .15s linear}
.fade.in {opacity: 1}
.modal-open {overflow: hidden}
.modal{ position:fixed; left:0; top:0; right:0; bottom:0; z-index:1040; display:none; overflow:hidden;-webkit-overflow-scrolling:touch; outline:0}
.modal.fade .modal-dialog{
	-webkit-transition: -webkit-transform .3s ease-out;
	-o-transition: -o-transform .3s ease-out;
	transition: transform .3s ease-out;
	-webkit-transform: translate(0,-50%);
	-ms-transform: translate(0,-50%);
	-o-transform: translate(0,-50%);
	transform: translate(0,-50%)}
.modal.in .modal-dialog {
	-webkit-transform: translate(0, 0);
	-ms-transform: translate(0, 0);
	-o-transform: translate(0, 0);
	transform: translate(0, 0)}
.modal-open .modal {overflow-x: hidden;overflow-y: auto}
	.modal-backdrop {position: fixed;top: 0;right: 0;bottom: 0;left: 0;background-color: #000}
	.modal-backdrop.fade {filter: alpha(opacity=0);opacity: 0}
	.modal-backdrop.in {filter: alpha(opacity=50);opacity: .5}
	.modal-dialog {position: relative;width: auto;margin: 10px}
	.modal-content{position: relative;background-color: #fff;border: 1px solid #999;border: 1px solid rgba(0,0,0,.2);outline: 0;
		-webkit-background-clip: padding-box;
		background-clip: padding-box; 
		-webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
		box-shadow: 0 3px 9px rgba(0,0,0,.5)}
	
		.modal-header {min-height: 16.42857143px;padding: 15px;border-bottom: 1px solid #eee; position:relative}
		.modal-header .close{position:absolute; right:10px; top:10px}
		.modal-header h3,.modal-header .modal-title{margin:0; padding:10px 0; font-size:16px}
	
		.modal-body {position:relative;padding: 15px;overflow-y:visible;word-break:break-all}
			.modal-form {margin-bottom: 0}
	
		.modal-footer {padding:15px;margin-bottom: 0;text-align: right;background-color: #f5f5f5;border-top: 1px solid #eee;*zoom: 1}
		.modal-footer:before,.modal-footer:after {display: table;content: ""}
		.modal-footer:after {clear: both}
		.modal-footer .btn + .btn {margin-left: 5px;margin-bottom: 0}
		.modal-footer .btn-group .btn + .btn {margin-left: -1px}
		.modal-footer .btn-block + .btn-block {margin-left: 0}
		
	.modal-scrollbar-measure {position: absolute;top: -9999px;width: 50px;height: 50px;overflow: scroll}
	
	.radius .modal-content{ border-radius:6px}
	.radius .modal-footer{ border-bottom-left-radius:6px; border-bottom-right-radius:6px}
	@media (min-width: 768px) {
		.modal-dialog {width: 600px;margin: 30px auto}
		.modal-content {-webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);box-shadow: 0 5px 15px rgba(0, 0, 0, .5)}
		.modal-sm {width: 300px}
	}
	@media (min-width: 992px) {
		.modal-lg {width: 900px}
	}
 
.modal-alert{position:fixed; right:auto; bottom:auto; width:300px; left:50%;margin-left:-150px; top:50%;margin-top:-30px; z-index:9999;background-color: #fff;border: 1px solid #999;border: 1px solid rgba(0,0,0,.2);outline: 0;
	-webkit-background-clip: padding-box;
	background-clip: padding-box; 
	-webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
	box-shadow: 0 3px 9px rgba(0,0,0,.5)}
.modal-alert-info{padding:30px; text-align:center; font-size:14px; background-color:#fff}


</style>


</head>
<body>
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content radius">
			<div class="modal-header">
				<h3 class="modal-title">附件正在上传</h3>
				<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
			</div>
			<div class="modal-body">
				<p>上传完成会自动关闭刷新界面~~~~请耐心等候~大文件的话可以去看会小说！！</p>
			</div>
<!-- 			<div class="modal-footer"> -->
<!-- 				<button class="btn btn-primary">确定</button> -->
<!-- 				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> -->
<!-- 			</div> -->
		</div>
	</div>
</div>
<article class="page-container">
	<form class="form form-horizontal" id="form-affix-add"  enctype="multipart/form-data" method="post"   > 
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>附件名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="affixName" name="affixName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">附件描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="affixDesc"  id="affixDesc" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">选择附件：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-thum-container">
					
					
					<input type="file" name="affix" id="affix"  >
				</div>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="affix_save_submit();" id="btns" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 上传附件</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>


<jsp:include page="../util/footer.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">

$(function(){
	//modaldemo();
});
function removeIframe(){
	 var index = parent.layer.getFrameIndex(window.name);
	 parent.layer.close(index);
}
function affix_save_submit(){
		var affixName = $("#affixName").val();
		
		if(affixName==""){
			$("#affixName").focus();
			return false;
		}else{
			if($('#affix').val().length>0){
				//ajax提交表单
				var oMyForm = new FormData($('#form-affix-add')[0]);
				$.ajax({
					 url:"${pageContext.request.contextPath}/affix/affixUpload.do",
		             type:"post",
		             data:oMyForm,
		             contentType : false,// 告诉jQuery不要去设置Content-Type请求头  
		             processData : false,// 告诉jQuery不要去处理发送的数据  
		             beforeSend: function () {
				       	modaldemo();
				     },
		             success:function(data){
		                    if(data=="ok"){
		                    	//layer.msg('附件上传成功!',{icon:1,time:3000});
		                    	/* removeIframe();
		                    	window.location.reload(true); */
		                    	//刷新页面
		                        parent.window.location.href='${pageContext.request.contextPath}/affix/affix_list.do';
		                        //获取窗口索引
		                        var index = parent.layer.getFrameIndex(window.name);
		                        //关闭弹出层
		                        parent.layer.close(index);
		                    }else{
		                    	layer.msg('附件上传失败!',{icon:1,time:3000});
		                    	
		                    }
		             }
				})
			}else{
				layer.msg('请选择上传附件!',{icon:1,time:3000});
			}
			
			
		
		}
	
}

function modaldemo(){
	$("#modal-demo").modal("show")
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>