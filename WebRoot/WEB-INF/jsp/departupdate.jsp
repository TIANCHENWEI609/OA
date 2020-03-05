<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>滴答办公系统-部门更新</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="/OA/media/layui/css/layui.css" media="all">
<script type="text/javascript" src="/OA/media/js/jquery.min.js"></script>
<script type="text/javascript" src="/OA/media/layui/layer/layer.js"></script>
<script type="text/javascript">
	//异步提交部门信息修改申请
	function updateDept(){
		var id= $("[name='deptId']").val();
		var name=$("[name='deptNm']").val();
		var time=$("[name='createdate']").val();
		$.post("/OA/upDeptInfo", {"id":id,"name":name,"crtTime":time}, function(result) {
			if(result.resCode==200){//修改成功，返回列表界面
				layer.msg(result.resInfo, {icon : 6,time: 1000,shade:0.4},//带遮盖层
					function(){//带有遮盖层，提示之后再刷新界面，视觉比较好
			   			window.location.href="/OA/page_departlist/1/5";//更新成功，返回列表
			   		});
			}else{//修改失败停留在当前页
				layer.msg(result.resInfo, {icon : 5,time: 1000,shade:0.4});
			}
		},"json");
	}
	
</script>


</head>
<body>

	<div class="layui-container" style="margin-top: 5px">
		<form class="layui-form" action="courseadd.do" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">部门编号</label>
				<div class="layui-input-block">
					<input type="text" name="deptId" readonly lay-verify="name" autocomplete="off"
						 class="layui-input" value="${Depart.id}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" name="deptNm" lay-verify="name" autocomplete="off"
						placeholder="请输入部门名称" class="layui-input" value="${Depart.name}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">创立日期</label>
				<div class="layui-input-block">
				 <input type="text" name="createdate" placeholder="请输入创立日期" id="date" autocomplete="off" class="layui-input" value="${Depart.createtime}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<!-- <input class="layui-btn" style="margin-left: 10%" type="submit" value="确认更新"> -->
				<input class="layui-btn" style="margin-left: 10%" type="button" value="确认更新" onclick="updateDept()"><!-- 使用ajax异步请求 -->
			</div>
		</form>
	</div>


	<script src="/OA/media/layui/layui.js"></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
	<script>
		layui.use(
						[ 'form', 'laydate' ],
						function() {
							var form = layui.form, layer = layui.layer, laydate = layui.laydate;

							//日期
							laydate.render({
								elem : '#date'
							});
							

							//自定义验证规则
							form.verify({
								title : function(value) {
									if (value.length < 5) {
										return '标题至少得5个字符啊';
									}
								},
								pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
								content : function(value) {
									layedit.sync(editIndex);
								}
							});
		});
	</script>
</body>
</html>
