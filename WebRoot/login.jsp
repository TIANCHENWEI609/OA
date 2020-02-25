<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>协同办公平台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<!-- load css -->
<link rel="stylesheet" type="text/css" href="media/layui/css/layui.css"
	media="all">
<link rel="stylesheet" type="text/css" href="media/css/login.css"
	media="all">
<link rel="stylesheet" type="text/css" href="media/css/verify.css">
<script type="text/javascript" src="media/js/jquery.min.js"></script>
<script type="text/javascript" src="media/layui/layer/layer.js"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">
	var loginIp="";//登录IP
	var loginCity="";//登录地点
	//页面加载就执行该方法（用这种方法进行页面初始化）
	$(function () {
            //获取IP
            // $('#logincity').val(returnCitySN["cname"]);
            loginIp=returnCitySN["cip"];
            $.ajax({
                url: 'http://api.map.baidu.com/location/ip?ak=ia6HfFL660Bvh43exmH9LrI6',
                type: 'POST',
                dataType: 'jsonp',
                success:function(data) {
                    //获取城市
                   loginCity=data.content.address_detail.province + "," + data.content.address_detail.city;
                }
            });
        })

	function empLogin(){
		var no=$("[name='no']").val();
		var pass=$("[name='pass']").val();
		$.post("/OA/emp_login", {"no":no,"pass":pass,"loginIp":loginIp,"loginCity":loginCity}, function(result) {
			if(result.resCode==200){
				//location.href="index.jsp";//登录成功进行页面跳转(不被保护的首月跳转)
				window.location.href="/OA/page_index";//登录成功进行页面跳转(被保护的首月跳转)
			}else{
				layer.alert("<a style='color:red'>"+result.resInfo+ "</a>", {icon:2,//需要指定颜色，不然底色是白色，不显示
				  skin: 'layui-layer-molv' //样式类名
				  ,closeBtn: 0
					}, function(){
				  window.location.reload();//刷新页面
				});
				
			}
		},"json");//ajax改为POST登录后，返回的要指定是json类型，要不然不识别！！！
	}
</script>

</head>
<body class="layui-bg-black">
	<div class="layui-canvs"></div>
	<div class="layui-layout layui-layout-login">
		<form action="/OA/emp_login" method="post">
			<h1>
				<strong>协同办公平台</strong> <em>Tick-tock Office System</em>
			</h1>
			<div class="layui-user-icon larry-login">
				<input type="text" placeholder="账号" class="login_txtbx" name="no" value="" />
			</div>
			<div class="layui-pwd-icon larry-login">
				<input type="password" placeholder="密码" name="pass" value="" class="login_txtbx" />
			</div>
			<input type="hidden" name="ip" id="ip1"> <input type="hidden"
				name="city" id="cy1">
			<div class="feri-code">
				<div id="mpanel4"></div>
			</div>
			<div class="layui-submit larry-login">
				<!-- <input type="submit" id="btn1" disabled="disabled" value="立即登陆"
					class="submit_btn" />同步请求，会刷新页面 -->
					<input type="button" id="btn1" disabled="disabled" value="立即登陆"
					class="submit_btn" onclick="empLogin()"/><!-- 异步ajax请求，无刷新登录 -->
			</div>
		</form>
		<div class="layui-login-text">
			<p>© 2016-2018 北京协同科技有限公司  版权所有</p>
		</div>
	</div>
	<script type="text/javascript" src="media/js/login.js"></script>
	<script type="application/javascript" src="media/js/verify.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//滑动验证码
			$('#mpanel4').pointsVerify({
				defaultNum : 6, //默认的文字数量
				checkNum : 1, //校对的文字数量
				vSpace : 5, //间隔
				imgName : [ '1.jpg', '2.jpg' ],
				imgSize : {
					width : '400px',
					height : '200px',
				},
				barSize : {
					width : '400px',
					height : '40px',
				},
				ready : function() {
				},
				success : function() {
					//......后续操作
					$("#btn1").attr("disabled", false);
				},
				error : function() {
				}
			});
		});
	</script>
</body>
</html>
