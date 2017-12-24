<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<!-- Mirrored from www.zi-han.net/theme/hplus/login_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:49 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>登录</title>

    <link href="./hplus/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="./hplus/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="./hplus/css/animate.min.css" rel="stylesheet">
    <link href="./hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="./hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="./hplus/css/login.min.css" rel="stylesheet">
    <script src="./hplus/js/jquery.min.js"  type="text/javascript"></script>
    <script src="./hplus/js/bootstrap.min.js"></script>
    <script src="./hplus/js/plugins/layer/layer.min.js"></script>
    <script src="./hplus/js/content.min.js"></script>
    <script src="./hplus/js/demo/layer-demo.min.js"></script>
    <script src="./hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="./hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="./hplus/js/plugins/layer/layer.min.js"></script>
    <script src="./hplus/js/hplus.min.js"></script>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <%--<jsp:include page="/WEB-INF/jsp/publicommon/scriptBase.jsp"></jsp:include>--%>
    <%--<script type="text/javascript"  src="${basePath}/script/loginmanager/login.js"></script>--%>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>

</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h4 class="logo-name">泵</h4>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>某泵系统管理</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="formedit"  role="form">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md"></p>
                <input type="text" class="form-control uname" name="userName" placeholder="用户名" />
                <input type="password" name="password" class="form-control pword m-b" placeholder="密码" />
                <a href="#">忘记密码了？</a>
                <button class="btn btn-success btn-block" name="loginsys">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2017 All Rights Reserved. For you
        </div>
    </div>
</div>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/login_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:52 GMT -->
</html>


