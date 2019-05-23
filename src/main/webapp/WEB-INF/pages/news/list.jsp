<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../util/meta.jsp"></jsp:include>

<title>新闻管理</title>
</head>
<body>
<jsp:include page="../util/header.jsp"></jsp:include>
<jsp:include page="../util/menu.jsp"></jsp:include>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		新闻管理
		<span class="c-gray en">&gt;</span>
		新闻列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a> </nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<form action="${pageContext.request.contextPath}/news/list.html" method="post" id="form1">
					日期范围：<input type="hidden" id="page" name="page" value="${pageInfo.pageNum}">
					<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"placeholder="开始时间"  id="datemin" class="input-text Wdate" style="width:150px;" name="startTime" value="${startTime}">
					-
					<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"placeholder="结束时间"  id="datemax" class="input-text Wdate" style="width:150px;" name="endTime" value="${endTime}">
					<input type="text" class="input-text" style="width:250px" placeholder="输入新闻名称" name="keyWord" value="${keyWord}">
					<span class="select-box inline">
						<select name="type" class="select">
							<option value="0">全部分类</option>
							<option ${type==1?'selected':''} value="1">公司新闻</option>
							<option ${type==2?'selected':''} value="2">行业新闻</option>
						</select>
					</span>
					<button type="submit" class="btn btn-success search"><i class="Hui-iconfont">&#xe665;</i> 搜新闻</button>
				</form>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"> 
					<a href="javascript:;" onclick="select('添加新闻','${pageContext.request.contextPath}/news/select.html')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加新闻</a>
				</span>
				<span class="r">当前页码：<strong >${pageInfo.pageNum}</strong> / <strong >${pageInfo.pages}</strong>&nbsp;&nbsp;&nbsp;  共有数据：<strong>${pageInfo.total}</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr><th scope="col" colspan="9">新闻列表</th></tr>
					<tr class="text-c">
						<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
						<th width="40">ID</th>
						<th >标题</th>
						<th width="100">作者</th>
						<th width="180">静态页面</th>
						<th width="80">新闻类型</th>
						<th width="150">图片</th>
						<th width="120">创建时间</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${pageInfo.list}">
					<tr class="text-c">
						<!-- <td><input type="checkbox" value="1" name=""></td> -->
						<td>${obj.id} </td>
						<td>${obj.title}</td>
						<td>${obj.author}</td>
						<c:if test="${obj.staticpage!=null }">
							<td>http://www.zjhbidding.com/viewpages/${obj.staticpage}</td>
						</c:if>
						<c:if test="${obj.staticpage==null }">
							<td>没有静态页面生成</td>
						</c:if>
						<td>${obj.type==1?'公司新闻':obj.type==2?'行业新闻':'未知'}</td>
						
						<c:if test="${obj.sysimg == null }"><td>暂时没有上传封面</td></c:if>
						<c:if test="${obj.sysimg != null }">
						<td><img style="width: 50px;height: 50px;"  src="${pageContext.request.contextPath}${obj.sysimg}"/></td>
						</c:if>
						<td><fmt:formatDate value="${obj.createtime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td class="td-manage">
							<a title="编辑" href="javascript:;" onclick="select('新闻编辑','${pageContext.request.contextPath}/news/select.html?id=${obj.id}','','')"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
							<a title="删除" href="javascript:;" onclick="del(this,'${obj.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			<div style="margin-top: 10px;text-align: center;">
				<input type="button" value="首 页" class="btn btn-primary radius" onclick="getPageView('first');">
				<input type="button" value="上一页" class="btn btn-primary radius" onclick="getPageView('prev');">
				<input type="button" value="下一页" class="btn btn-primary radius" onclick="getPageView('next');">
				<input type="button" value="末 页" class="btn btn-primary radius" onclick="getPageView('last');">
			</div>
		</article>
	</div>
</section>

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

/*新闻-删除*/
function del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			url:"${pageContext.request.contextPath}/news/delete.shtml",
            type:"post",
            data:{id:id},
            success:function(data){
            	if (data == 'success'){
            		$(obj).parents("tr").remove();
            		layer.msg('已删除!',{icon:1,time:1000});
            	}
            }
		})
	});
}

/*新闻-增加/编辑*/
// function select(title,url,w,h){
// 	layer_show(title,url,w,h);
// }
function select(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

function getPageView(type){
	var currentTotalPage = Number("${pageInfo.pages}");
	if(type=="first"){
		var currentPageNum = Number($("#page").val());
		if(currentPageNum>1){
			$("#page").val("1");
			$("#form1").submit();
		}
	}else if(type=="prev"){
		var currentPageNum = Number($("#page").val());
		
		if(currentPageNum<=1){
			layer.msg('已经是第一页了!',{icon:2,time:2000});
			
		}else{
			$("#page").val(currentPageNum-Number(1));
			$("#form1").submit();
		}
		
	}else if(type=="next"){
		var currentPageNum = Number($("#page").val());
		if(currentPageNum>=currentTotalPage){
			layer.msg('已经最后一页了!',{icon:2,time:2000});
		}else{
			$("#page").val(currentPageNum+Number(1));
			$("#form1").submit();
		}
		
	}else if(type=="last"){
		var currentPageNum = Number($("#page").val());
		if(currentPageNum<currentTotalPage){
			$("#page").val(currentTotalPage);
			$("#form1").submit();
		}
	}
}
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>