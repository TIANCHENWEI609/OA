<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>协同办公平台-部门新增</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="media/layui/css/layui.css" media="all">

<script type="text/javascript" src="media/js/jquery.min.js"></script>
<script type="text/javascript" src="media/layui/layer/layer.js"></script>
<!-- 引入layui的提示样式 -->
<script type="text/javascript">
	//通过ajax实现异步登录实现无页面刷新新增
	function addDepart(){
		var name=$("[name='name']").val();
		var createtime=$("[name='createtime']").val();
		$.post('/OA/addDepart',{"name":name,"createtime":createtime},
		function(result){
			if(result.resCode==200){//新增成功
			layer.alert("<a style='color:red'>"+result.resInfo+ "</a>", //需要指定颜色，不然底色是白色，不显示
			{icon:1, skin: 'layui-layer-molv',closeBtn: 0}, //设置样式
			function(){
				  window.location.href="/OA/page_departlist";//指向部门列表界面
				});
			}else{//新增失败,清空表单
				layer.alert("<a style='color:red'>"+result.resInfo+ "</a>", //需要指定颜色，不然底色是白色，不显示
					{icon:2, skin: 'layui-layer-molv',closeBtn: 0}, //设置样式
					function(index){//加一个参数进行关闭弹窗
						 $("[name='name']").val("");
						 $("[name='createtime']").val("");
						 layer.close(index);//关闭弹窗
					});
			}
		},"json");
	}

</script>

</head>
<body>
	<div class="layui-container" style="margin-top: 5px">
		<form class="layui-form" action="/OA/addDepart" method="post">
		<!-- 部门编号不需要 -->
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">部门编号</label>
				<div class="layui-input-block">
					<input type="text" name="name" readonly lay-verify="name" autocomplete="off"
						 class="layui-input">
				</div>
			</div> -->
			
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" id="partName" name="name" lay-verify="name" autocomplete="off"
						placeholder="请输入部门名称" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">创立日期</label>
				<div class="layui-input-block">
				 <input type="text" name="createtime" placeholder="请输入创立日期" id="date" autocomplete="off" class="layui-input">
				</div>
			</div>
			<!-- 同步表单提交（界面会刷新，体验一般） -->
			<!-- <div class="layui-form-item">
				<input class="layui-btn" style="margin-left: 10%" type="submit" value="确认新增">
			</div> -->
			<!-- 异步ajax提交（界面不刷新，体验较好） -->
			<div class="layui-form-item">
				<input class="layui-btn" style="margin-left: 10%" type="button" value="确认新增" onclick="addDepart()">
			</div>
		</form>
	</div>


	<script src="media/layui/layui.js"></script>
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
