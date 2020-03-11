<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>.Net海</title>
    <link href="media/layui/css/layui.css" rel="stylesheet" />    //注意路劲
  
    <style>
        ol li a {
            background-color: rgba(0,0,0,1) !important;
        }

        .three_this {
            background-color: #808080 !important;
        }
    </style>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">layui 后台布局</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item"><a href="">商品管理</a></li>
                <li class="layui-nav-item"><a href="">用户管理</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">日常</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="~/images/tb.png" class="layui-nav-img" />
                        .Net海
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">退出</a></li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">所有商品</a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="menu_three ">列表一</a>
                                <ol class="layui-nav-child" style="display: none;">
                                    <li><a href="javascript:;">三级列表一</a></li>
                                    <li><a href="javascript:;">三级列表二</a></li>
                                    <li><a href="javascript:;">三级列表三</a></li>
                                    <li><a href="">超链接</a></li>
                                </ol>
                            </dd>
                            <dd><a href="javascript:;" class="menu_three">列表二</a>
                                <ol class="layui-nav-child" style="display: none;">
                                    <li><a href="javascript:;">三级列表一</a></li>
                                    <li><a href="javascript:;">三级列表二</a></li>
                                    <li><a href="javascript:;">三级列表三</a></li>
                                    <li><a href="">超链接</a></li>
                                </ol>
                            </dd>
                            <dd><a href="javascript:;">列表三</a></dd>
                            <dd><a href="">超链接</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="">云市场</a></li>
                    <li class="layui-nav-item"><a href="">发布商品</a></li>
                </ul>
            </div>
        </div>

        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">内容主体区域</div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © layui.com - 底部固定区域
        </div>
    </div>
 //注意路劲
    <script src="media/layui/layui.all.js"></script>
    <script>
        //JavaScript代码区域
        layui.use(['element', 'jquery'], function () {
            var element = layui.element, $ = layui.jquery;
            $(".menu_three").on("click", function () {
                
                $(this).next().toggle();
                $.each($(this).parent().siblings(), function (i, e) {
                    $(e).find("ol").hide();;
                });
               
            })
            $("ol").on("click", "li a", function () {
                $.each($(this).parent().siblings(), function (i, e) {
                    $(e).find("a").removeClass('three_this')
                });
                $(this).addClass('three_this');                            // 添加当前元素的样式
            })
        });
    </script>
</body>
</html>
