<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>协同办公平台-部门列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="/OA/media/layui/css/layui.css" media="all">
<script src="/OA/media/js/jquery.min.js"></script>
<script type="text/javascript" src="/OA/media/layui/layer/layer.js"></script>
<script type="text/javascript">
	//动态设置页面大小
	//切换页面大小，需要重新发起首页请求
	function pageSizeJS(select){
		var pageSize=$(select).val();
		location.href="/OA/page_departlist/1/"+pageSize;
	}
	
	function goPage(){
		var pageIndex=$("#pageInfo").val();
		//校验页码信息，必须是正整数，不能超过总数，不能是字母啥的非法字符
		if((/(^[1-9]\d*$)/.test(pageIndex))&&(pageIndex<=${PageUtil.pageCount})){//正整数
			location.href="/OA/page_departlist/"+pageIndex+"/"+${PageUtil.pageSize};
		}else{//非整数
			layer.alert("<a style='color:red'>页码信息错误，请确认!</a>", {icon:2,//需要指定颜色，不然底色是白色，不显示
				  skin: 'layui-layer-molv' //样式类名
				   ,closeBtn: 0
					}, function(index){//给一个insex可以关闭提示框
					layer.close(index)
					$("#pageInfo").focus();//聚焦在可输入页数的位置
				});
		}
	}
</script>
</head>
<body>
	<div class="layui-container">
		<table class="layui-table" id="tbdata" lay-filter="tbop">
			<thead>
				<tr>
					<td>部门编号</td>
					<td>部门名称</td>
					<td>部门人数</td>
					<td>成立日期</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${PageUtil.records}" var="dept">
					<tr>
						<td>${dept.id}</td>
						<td>${dept.name}</td>
						<td>0稍后统计</td>
						<td>${dept.createtime}</td>
						<td><a class="layui-btn layui-btn-mini" href="/OA/upDeptInfo?id=${dept.id}">编辑</a>
							<a class="layui-btn layui-btn-danger layui-btn-mini"
							lay-event="del" onclick="deleteDepart(${dept.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
		·					<!-- 设置不可用方法 -->
						   <!-- <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0"> -->
						   <a href="/OA/page_departlist/${PageUtil.pageIndex==1?1:PageUtil.pageIndex-1}/${PageUtil.pageSize}" class="layui-laypage-prev ${PageUtil.pageIndex==1?'layui-disabled':''}" data-page="0">
							    <i class="layui-icon">&lt;</i>
						   </a> 
								<c:forEach begin="${PageUtil.pageStart}" end="${PageUtil.pageEnd}" var="itemYM" step="1">
									<c:if test="${PageUtil.pageIndex==itemYM}"><!-- 将当前页码标红并设置不能点击 -->
										<span style="color: red" font>${itemYM}</span>
									</c:if>
									<c:if test="${PageUtil.pageIndex!=itemYM}"><!-- 非当前页码则不进行标红 ，可以点击-->
										<a href="/OA/page_departlist/${itemYM}/${PageUtil.pageSize}">${itemYM}</a>
									</c:if>
								</c:forEach>
							<!-- <a href="javascript:;" class="layui-laypage-next layui-disabled" data-page="2"> --> 
							<a href="/OA/page_departlist/${PageUtil.pageIndex==PageUtil.pageCount?PageUtil.pageCount:PageUtil.pageIndex+1}/${PageUtil.pageSize}" class="layui-laypage-next ${PageUtil.pageIndex==PageUtil.pageCount?'layui-disabled':''}" data-page="2"> 
							    <i class="layui-icon">&gt;</i>
							</a> 
							<span class="layui-laypage-skip">到第
							   <input type="text" id="pageInfo" min="1" value="${PageUtil.pageIndex}" class="layui-input">页
								<button type="button" class="layui-laypage-btn" onclick="goPage()">确定</button>
							</span> 
							<span class="layui-laypage-count">共 ${PageUtil.pageCount}页 ${PageUtil.totalCount}条</span> 
							<span class="layui-laypage-limits"> 
							    <select lay-ignore="" onchange="pageSizeJS(this)"><!-- 每页条数 -->
							    <!-- 注意设置默认的条数显示 -->
							        <option id="option5" value="5" ${PageUtil.pageSize==5?"selected":""}>5 条/页</option>
									<option id="option10" value="10" ${PageUtil.pageSize==10?"selected":""}>10 条/页</option>
									<option id="option20" value="20" ${PageUtil.pageSize==20?"selected":""}>20 条/页</option>
									<option id="option30" value="30" ${PageUtil.pageSize==30?"selected":""}>30 条/页</option>
									<option id="option40" value="40" ${PageUtil.pageSize==40?"selected":""}>40 条/页</option>
							</select>
							</span>
						</div>
	</div>
	<script src="/OA/media/layui/layui.js"></script>
	
	<script type="text/javascript">
	   function deleteDepart(id){
		   layui.use('table', function() {
			   layer.confirm('是否确认删除部门?',function(index) {
			   		//确认删除发送ajax通讯进行删除部门（更新del标识，进行假删除）
			   		$.getJSON("/OA/delete_depart", {"id":id}, function(result) {
			   			if(result.resCode==200){
			   				layer.msg("删除成功", {icon : 6,time: 1000,shade:0.4},function(){//带有遮盖层，提示之后再刷新界面，视觉比较好
			   					window.location.reload();//删除成功刷新页面
			   				});
			   			}else{
			   				layer.msg("删除失败", {icon : 5,time: 1000,shade:0.4});
			   			}
			   		})
			   });
		   });
	   }
	</script>
	
	
</body>
</html>
