<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>协同办公系统-员工列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="/OA/media/layui/css/layui.css" media="all">
<script src="/OA/media/js/jquery.min.js"></script>
<script type="text/javascript" src="/OA/media/layui/layer/layer.js"></script>
<script type="text/javascript">
	function setPageSize(select){
		var pageSize=$(select).val();
		location.href="/OA/page_Emptlist/1/"+pageSize;
	}
	function goPage(){
		//页码的校验
		var pageIndex=$("#pageQT").val();
		//校验页码信息，必须是正整数，不能超过总数，不能是字母啥的非法字符
		if((/(^[1-9]\d*$)/.test(pageIndex))&&(pageIndex<=${empPageUtil.pageCount})){//正整数
			location.href="/OA/page_Emptlist/"+pageIndex+"/"+${empPageUtil.pageSize};
		}else{//非整数
			layer.alert("<a style='color:red'>页码信息错误，请确认!</a>", {icon:2,//需要指定颜色，不然底色是白色，不显示
				  skin: 'layui-layer-molv' //样式类名
				   ,closeBtn: 0
					}, function(index){//给一个insex可以关闭提示框
					layer.close(index)
					$("#pageQT").focus();//聚焦在可输入页数的位置
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
					<td hidden="hidden">序号</td>
					<td>工号</td>
					<td>姓名</td>
					<td>部门</td>
					<td>性别</td>
					<td>手机号</td>
					<td>QQ号</td>
					<td>邮箱</td>
					<td>入职日期</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${empPageUtil.records}" var="empInfo">
					<tr>
						<td hidden="hidden">${empInfo.id}</td>
						<td>${empInfo.no}</td>
						<td>${empInfo.name}</td>
						<td>${empInfo.depart.name}</td>
						<td>${empInfo.sex}</td>
						<td>${empInfo.phone}</td>
						<td>${empInfo.qq}</td>
						<td>${empInfo.email}</td>
						<td>${empInfo.createdate}</td>
						<td align='center' valian='middle'>
						<!-- layui-btn-sm小型按钮，更美观 -->
							<a class="layui-btn layui-btn-sm" href="/OA/toEmpUpdPg/${empInfo.id}" style="width: 50">编辑</a>
							<a class="layui-btn layui-btn-danger layui-btn-sm" style="width: 50"
							lay-event="del" onclick="deleteCourse(${empInfo.id});">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
						   <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0">
							    <i class="layui-icon">&lt;</i>
						   </a> 
							    <!-- 注意：begin和end里面变量为var ,不为item!!!  -->
							    <c:forEach begin="${empPageUtil.pageStart}" end="${empPageUtil.pageEnd}" var="pageInfo" step="1">
							   		<c:if test="${empPageUtil.pageIndex==pageInfo}">
							   			 <a style="color: red">${pageInfo}</a>
							   		</c:if>
							   		<c:if test="${empPageUtil.pageIndex!=pageInfo}">
							   			 <a href="/OA/page_Emptlist/${pageInfo}/${empPageUtil.pageSize}">${pageInfo}</a>
							   		</c:if>
							   	</c:forEach>
							<a href="javascript:;" class="layui-laypage-next layui-disabled" data-page="2"> 
							    <i class="layui-icon">&gt;</i>
							</a> 
							<span class="layui-laypage-skip">到第
							   <input type="text" min="1" value="${empPageUtil.pageIndex}" id="pageQT" class="layui-input">页
								<button type="button" class="layui-laypage-btn" onclick="goPage();">确定</button>
							</span> 
							<span class="layui-laypage-count">共 ${empPageUtil.pageCount}页/${empPageUtil.totalCount} 条</span> 
							<span class="layui-laypage-limits"> 
							    <select lay-ignore="" onchange="setPageSize(this);">
							        <option value="5" ${empPageUtil.pageSize==5?"selected":""}>5 条/页</option>
									<option value="10" ${empPageUtil.pageSize==10?"selected":""}>10 条/页</option>
									<option value="20" ${empPageUtil.pageSize==20?"selected":""}>20 条/页</option>
									<option value="30" ${empPageUtil.pageSize==30?"selected":""}>30 条/页</option>
									<option value="40" ${empPageUtil.pageSize==40?"selected":""}>40 条/页</option>
								</select>
							</span>
						</div>
	</div>
	
	<script src="/OA/media/layui/layui.js"></script>
	
	<script type="text/javascript">
	   function deleteCourse(id){
		   layui.use('table', function() {
			   layer.confirm('是否确认删除员工?',function(index) {
				  //确认删除发送通讯进行员工删除
				  $.getJSON("/OA/emp_Del",{"id":id},
				  		function(result){
				  		if(result.resCode==200){
			   				layer.msg("删除成功", {icon : 6,time: 1000,shade:0.4},function(){//带有遮盖层，提示之后再刷新界面，视觉比较好
			   					window.location.reload();//删除成功刷新页面
			   				});
			   			}else{
			   				layer.msg("删除失败", {icon : 5,time: 1000,shade:0.4});
			   			}
				  });
			   });
		   });
	   }
	</script>

</body>
</html>
