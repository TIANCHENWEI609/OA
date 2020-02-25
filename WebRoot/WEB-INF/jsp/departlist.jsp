<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>协同办公平台-部门列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="media/layui/css/layui.css" media="all">
<script src="media/js/jquery.min.js"></script>
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
				<tr>
					<td>01</td>
					<td>教学部</td>
					<td>50</td>
					<td>2015-10-01</td>
					<td><a class="layui-btn layui-btn-mini" href="departupdate.html">编辑</a>
						<a class="layui-btn layui-btn-danger layui-btn-mini"
						lay-event="del" onclick="deleteCourse();">删除</a></td>
				</tr>
				<tr>
					<td>02</td>
					<td>就业部</td>
					<td>50</td>
					<td>2015-10-01</td>
					<td><a class="layui-btn layui-btn-mini" href="departupdate.html">编辑</a>
						<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteCourse();">删除</a></td>
				</tr>
				<tr>
					<td>03</td>
					<td>教务部</td>
					<td>50</td>
					<td>2015-10-01</td>
					<td><a class="layui-btn layui-btn-mini" href="departupdate.html">编辑</a>
						<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteCourse();">删除</a></td>
				</tr>
			</tbody>
		</table>
		<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
						   <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0">
							    <i class="layui-icon">&lt;</i>
						   </a> 
							    <span style="color:#009688;font-weight: bold;">1</span> 
							    <a href="#">2</a> 
							    <a href="#">3</a>  
							    <a href="#">4</a> 
							    <a href="#">5</a> 
							<a href="javascript:;" class="layui-laypage-next layui-disabled" data-page="2"> 
							    <i class="layui-icon">&gt;</i>
							</a> 
							<span class="layui-laypage-skip">到第
							   <input type="text" min="1" value="1" class="layui-input">页
								<button type="button" class="layui-laypage-btn">确定</button>
							</span> 
							<span class="layui-laypage-count">共 1 条</span> 
							<span class="layui-laypage-limits"> 
							    <select lay-ignore="">
							        <option value="10" selected="">10 条/页</option>
									<option value="20">20 条/页</option>
									<option value="30">30 条/页</option>
									<option value="40">40 条/页</option>
									<option value="50">50 条/页</option>
									<option value="60">60 条/页</option>
									<option value="70">70 条/页</option>
									<option value="80">80 条/页</option>
									<option value="90">90 条/页</option>
							</select>
							</span>
						</div>
	</div>
	<script src="media/layui/layui.js"></script>
	
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
