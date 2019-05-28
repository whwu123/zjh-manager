<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../util/meta.jsp"></jsp:include>

<title>附件管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 附件管理<span class="c-gray en">&gt;</span> 回收站 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
		<form action="${pageContext.request.contextPath}/affix/recycle_list.do" method="post" id="checkForm">
			<div class="text-c"> 日期范围：
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" placeholder="开始时间"  id="datemin" class="input-text Wdate" style="width:150px;" name="startTime" value="${startTime }">
				-
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" placeholder="结束时间"  id="datemax" class="input-text Wdate" style="width:150px;" name="endTime" value="${endTime }">
				<input type="text" class="input-text" style="width:250px" placeholder="输入附件名称" id="affixName" name="affixName" value="${affixName}">
				<input  id="currentPageNum"  type="hidden" name="pageNum" value="${pageView.pageNum }"/>
				<input  id="currentTotalPage"  type="hidden" name="currentTotalPage" value="${pageView.totalPage }"/>
				<button type="button" class="btn btn-success" id="checkAffix" name="checkAffix" onclick="shouAffix();"><i class="Hui-iconfont">&#xe665;</i> 搜附件</button>
			</div>
		</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20" style="line-height: 30px;">
				<span class="l"> 
<!-- 				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->
				<span class="r">当前页码：<strong >${pageView.pageNum }</strong> / <strong >${pageView.totalPage }</strong>&nbsp;&nbsp;&nbsp;  共有数据：<strong id="affixTotalCount">${pageView.totalCount }</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="9">附件列表</th>
					</tr>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="40">ID</th>
						<th width="150">名称</th>
						<th width="200">描述</th>
						<th width="60">类型</th>
						<th width="50">大小</th>
						<th width="120">创建时间</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="affix" items="${pageView.listData }">
					<tr class="text-c">
						<td><input type="checkbox" value="1" name=""></td>
						<td>${affix.id }</td>
						<td>${affix.affixname }</td>
						<td>${affix.descrption }</td>
						<td>${affix.affixtype }</td>
						<td>
							<fmt:formatNumber type="number" value="${affix.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  M
						</td>
						<td>
							<fmt:formatDate value="${affix.affixtime  }" pattern="yyyy-MM-dd HH:mm"/>
						</td>
	
						<td class="td-manage">
							<a title="撤销" style="text-decoration:none" onClick="affix_back(this,'${affix.id}')" href="javascript:;" ><i class="Hui-iconfont">&#xe66b;</i></a>
							<a title="删除" href="javascript:;" onclick="affix_realdel(this,'${affix.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe60b;</i></a>
						 </td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			<div style="margin-top: 10px;text-align: center;">
			<input type="button" value="首 页"  class="btn btn-primary radius"  onclick="getPageView('first');">
			<input type="button" value="上一页" class="btn btn-primary radius" onclick="getPageView('prev');">
			<input type="button" value="下一页"  class="btn btn-primary radius" onclick="getPageView('next');">
			<input type="button" value="末 页"  class="btn btn-primary radius" onclick="getPageView('last');">
			</div>
		</article>
		
	</div>

<jsp:include page="../util/footer.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*附件-增加*/
function my_affix_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*附件-下载*/
function affix_back(obj,id){
	layer.confirm('确定恢复附件？',function(index){
		$.ajax({
			url:'${pageContext.request.contextPath}/affix/affixBack.do',
			type:'post',
			data:{id:id},
			success:function(data){
				if(data=='ok'){
					$(obj).parents("tr").remove();
					layer.msg('撤销成功!',{icon:1,time:1500});
				}else{
					layer.msg('撤销失败!',{icon:2,time:1500});
				}
			}
		});
	})
	
}
/*附件-删除*/
function affix_realdel(obj,id){
	layer.confirm('删除后将无法恢复！确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			url:"${pageContext.request.contextPath}/affix/affix_realdel.do",
            type:"post",
            data:{id:id},
            success:function(data){
 				if(data=='ok'){
 					$(obj).parents("tr").remove();
 					layer.msg('已删除!',{icon:1,time:1000});
 				}else{
 					layer.msg('删除附件失败!',{icon:1,time:3000});
 				}
            }
		})
	});
}

function getPageView(type){
	var currentTotalPage = Number($("#currentTotalPage").val());
	if(type=="first"){
		var currentPageNum = Number($("#currentPageNum").val());
		if(currentPageNum>1){
			$("#currentPageNum").val("1");
			$("#checkForm").submit();
		}
	}else if(type=="prev"){
		var currentPageNum = Number($("#currentPageNum").val());
		
		if(currentPageNum<=1){
			layer.msg('已经是第一页了!',{icon:2,time:2000});
			
		}else{
			$("#currentPageNum").val(currentPageNum-Number(1));
			$("#checkForm").submit();
		}
		
	}else if(type=="next"){
		var currentPageNum = Number($("#currentPageNum").val());
		if(currentPageNum>=currentTotalPage){
			layer.msg('已经最后一页了!',{icon:2,time:2000});
		}else{
			$("#currentPageNum").val(currentPageNum+Number(1));
			$("#checkForm").submit();
		}
		
	}else if(type=="last"){
		var currentPageNum = Number($("#currentPageNum").val());
		if(currentPageNum<currentTotalPage){
			$("#currentPageNum").val(currentTotalPage);
			$("#checkForm").submit();
		}
	}
}

function shouAffix(){
	$("#currentPageNum").val("1");
	$("#checkForm").submit();
}


</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>