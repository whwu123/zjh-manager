<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../util/meta.jsp"></jsp:include>

<title>留言管理</title>
</head>
<body>
<%-- <jsp:include page="../util/header.jsp"></jsp:include>
<jsp:include page="../util/menu.jsp"></jsp:include> --%>

<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 留言管理<span class="c-gray en">&gt;</span> 留言列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="${pageContext.request.contextPath}/note/note_list.do" method="post" id="checkForm">
			<div class="text-c"> 日期范围：
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" placeholder="开始时间"  id="datemin" class="input-text Wdate" style="width:150px;" name="startTime" value="${startTime }">
				-
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" placeholder="结束时间"  id="datemax" class="input-text Wdate" style="width:150px;" name="endTime" value="${endTime }">
				<input type="text" class="input-text" style="width:250px" placeholder="输入留言内容" id="content" name="content" value="${content}">
				<input  id="currentPageNum"  type="hidden" name="pageNum" value="${pageView.pageNum }"/>
				<input  id="currentTotalPage"  type="hidden" name="currentTotalPage" value="${pageView.totalPage }"/>
				<button type="button" class="btn btn-success" id="checkAffix" name="checkAffix" onclick="shouAffix();"><i class="Hui-iconfont">&#xe665;</i> 搜留言</button>
			</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="r">当前页码：<strong >${pageView.pageNum }</strong> / <strong >${pageView.totalPage }</strong>&nbsp;&nbsp;&nbsp;  
				共有数据：<strong id="affixTotalCount">${pageView.totalCount }</strong> 条</span>
			
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="60">ID</th>
							<th>内容</th>
							<th width="100">姓名</th>
							<th width="150">联系方式</th>
							<th width="200">公司名称</th>
							<th width="120">更新时间</th>
						
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="note" items="${pageView.listData }">
						<tr class="text-c">
							<td><input type="checkbox" value="" name=""></td>
							<td>${note.id }</td>
							<td class="text-l">${note.content}</td>
							<td>${note.nickname }</td>
							<td>${note.phone }</td>
							<td>${note.companyname }</td>
							<td>
						
 								<fmt:formatDate value="${note.createtime}" pattern="yyyy-MM-dd HH:mm"/> 
							</td>
							
							<td class="f-14 td-manage">
								<a style="text-decoration:none" onClick="note_view('查看留言','${pageContext.request.contextPath}/note/note_view.html?id=${note.id}')" href="javascript:;" title="查看"><i class="Hui-iconfont">&#xe695;</i></a>
								<a style="text-decoration:none" class="ml-5" onClick="note_del(this,'${note.id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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

/*留言-删除*/
function note_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/note/delete.do',
			data:{id:id},
			success: function(data){
				if(data=='ok'){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1500});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}


/*留言-查看*/
function note_view(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>