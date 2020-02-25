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
<!-- 打开页面请求ajaxj进行初始化查询 -->
</head>
<body>
	<div class="layui-container">
		<table class="layui-table" id="tbdata" lay-filter="tbop">
			<thead>
				<tr>
					<td>序号</td>
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
						<td><a class="layui-btn layui-btn-mini" href="departupdate.html">编辑</a>
							<a class="layui-btn layui-btn-danger layui-btn-mini"
							lay-event="del" onclick="deleteCourse();">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
						   <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0">
							    <i class="layui-icon">&lt;</i>
						   </a> 
								<c:forEach begin="${PageUtil.pageStart}" end="${PageUtil.pageEnd}" var="itemYM" step="1">
									<a href="/OA/page_departlist/${itemYM}/5">${itemYM}</a> 
								</c:forEach>
							<a href="javascript:;" class="layui-laypage-next layui-disabled" data-page="2"> 
							    <i class="layui-icon">&gt;</i>
							</a> 
							<span class="layui-laypage-skip">到第
							   <input type="text" min="1" value="1" class="layui-input">页
								<button type="button" class="layui-laypage-btn">确定</button>
							</span> 
							<span class="layui-laypage-count">共 ${PageUtil.pageCount}页 ${PageUtil.totalCount}条</span> 
							<span class="layui-laypage-limits"> 
							    <select lay-ignore="" id="numOfPage"><!-- 每页条数 -->
							        <option id="option5" value="5" selected="">5 条/页</option>
									<option id="option10" value="10">10 条/页</option>
									<option id="option20" value="20">20 条/页</option>
									<option id="option30" value="30">30 条/页</option>
									<option id="option40" value="40">40 条/页</option>
							</select>
							</span>
						</div>
	</div>
	<script src="/OA/media/layui/layui.js"></script>
	
	<script type="text/javascript">
	   function deleteCourse(){
		   layui.use('table', function() {
			   layer.confirm('是否确认删除部门?',function(index) {
				   layer.msg("删除成功", {icon : 6});
				   layer.msg("删除失败", {icon : 5});
			   });
		   });
	   }
	</script>
	
	
</body>
</html>
