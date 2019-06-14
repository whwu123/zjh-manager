<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../../util/meta.jsp"></jsp:include>

<title>角色管理</title>
</head>
<body>

<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理<span class="c-gray en">&gt;</span> 角色管理<span class="c-gray en">&gt;</span>角色列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
		<form action="${pageContext.request.contextPath}/admin/admin_roles.do" method="post" id="checkForm">
			<div class="text-c"> 创建日期：
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="datemin" placeholder="开始时间"  class="input-text Wdate" style="width:150px;" name="startTime" value="${startTime }">
				-
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="datemax" placeholder="结束时间"  class="input-text Wdate" style="width:150px;" name="endTime" value="${endTime }">
				<input type="text" class="input-text" style="width:250px" placeholder="输入角色名称" id="rName" name="rName" value="${rName}">
				<span class="select-box" style="width: 120px;">
				状态：
	  				<select name="rStatus" class="select"  size="1" name="demo1" style="width: 55px;">
							<option value="1" <c:if test="${dStatus==1 }">selected="selected" </c:if>>启用</option>
							<option value="0" <c:if test="${dStatus==0 }">selected="selected" </c:if> >禁用</option>
					</select>
				</span>
				<input  id="currentPageNum"  type="hidden" name="pageNum" value="${pageView.pageNum }"/>
				<input  id="currentTotalPage"  type="hidden" name="currentTotalPage" value="${pageView.totalPage }"/>
				<button type="button" class="btn btn-success" id="checkAffix" name="checkAffix" onclick="shouAffix();"><i class="Hui-iconfont">&#xe665;</i> 搜角色</button>
			</div>
		</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20" style="line-height: 30px;">
				<span class="l"> 
<!-- 				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->
				<a href="javascript:;" onclick="my_dept_add('添加角色','${pageContext.request.contextPath}/admin/role/add_role.do','900','400')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span>
				<span class="r">当前页码：<strong >${pageView.pageNum }</strong> / <strong >${pageView.totalPage }</strong>&nbsp;&nbsp;&nbsp;  共有数据：<strong id="affixTotalCount">${pageView.totalCount }</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<!-- <tr>
						<th scope="col" colspan="9">链部门列表</th>
					</tr> -->
					<tr class="text-c">
						<th width="40">排序号</th>
						<th width="100">编号</th>
						<th width="150">名称</th>
						<th width="120">创建时间</th>
						<th width="50">状态</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${pageView.listData }" varStatus="vs">
					<tr class="text-c">
						<td>${item.rSort }</td>
						<td>${item.rCode }</td>
						<td>${item.rName }</td>
						<td>
							<fmt:formatDate value="${item.rDate  }" pattern="yyyy-MM-dd HH:mm"/>
						</td>
						<%-- <td>
							<c:if test="${item.dStatus == 1 }"><a style="text-decoration: none;color: #429842" onclick="chageStatus('${item.dId }',0);"><i class="Hui-iconfont">&#xe615;</i>启用</a></c:if>
							<c:if test="${item.dStatus == 0 }"><a style="text-decoration: none;" onclick="chageStatus('${item.dId }',1);"><i class="Hui-iconfont">&#xe601;</i>禁用</a></c:if>
						</td> --%>
						<td>
							<c:if test="${item.rStatus == 1 }"><i class="Hui-iconfont">&#xe615;</i>启用</c:if>
							<c:if test="${item.rStatus == 0 }"><i class="Hui-iconfont">&#xe601;</i>禁用</c:if>
						</td>
						<td class="td-manage">
							<a title="查看" style="text-decoration:none" onClick="show_item('查看','${pageContext.request.contextPath}/admin/role/add_role.do?rId=${item.rId }')" href="javascript:;" ><i class="Hui-iconfont">&#xe6df;</i></a>
							<a title="删除" href="javascript:;" onclick="items_del(this,'${item.rId }')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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

<jsp:include page="../../util/footer.jsp"></jsp:include>

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
function my_dept_add(title,url,w,h){
	layer_show(title,url,w,h);
}

function show_item(title,url){
	/* var index = layer.open({
		type: 2,
		title: title,
		content: url,
		width:900,
		heigth:500
	});
	layer.full(index); */
	layer_show(title,url,900,400);
	
}
/*附件-删除*/
function items_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/role/role_del.do",
            type:"post",
            data:{rId:id},
            success:function(data){
 				if(data == "success"){
 					$(obj).parents("tr").remove();
 					layer.msg('已删除!',{icon:1,time:1000});
 				}else{
 					layer.msg('删除失败!',{icon:1,time:3000});
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
/* function chageStatus(id,status){
	var message = "";
	if(status == 1){
		message = "确定启用此配置吗？";
	}else if(status == 0 ){
		message = "确定禁用此配置吗？"
	}
	layer.confirm(message,function(index){
	$.ajax({
			url:"${pageContext.request.contextPath}/admin/update_base.do",
            type:"post",
            data:{fId:id,status:status},
            success:function(data){
 				if(data == "success"){
 					 //window.location.href='${pageContext.request.contextPath}/admin/system-base.html';
					//刷新当前窗口
 					self.location.reload();
 				}else{
 					layer.msg('修改失败!',{icon:1,time:3000});
 				}
            }
		})
	});
} */

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>