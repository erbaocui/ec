<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>电子烟管理平台</title>
	<link href="<%=request.getContextPath()%>/css/base.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/login/login.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.easyui.min.js"></script>

</head> 
<body class="default">
<form id="adminlogin" method=post name="adminlogin" action="${pageContext.request.contextPath}/admin/login.do">
	<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<span class="logo"></span>
			<span class="split"></span>
			<span class="sys-name">电子烟管理平台</span>
		</div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label"><h4>用户登录</h4></div>
						<div class="alert alert-error">
			              <i class="iconfont">&#xe62e;</i>
			              <span>请输入用户名</span>
			            </div>

							<div class="lg-username input-item clearfix">
							<i class="iconfont">&#xe60d;</i>
							<input type="text" id="userName" />
						    </div>
							<div class="lg-password input-item clearfix">
								<i class="iconfont">&#xe634;</i>
								<input type="password"  id="password" />
							</div>
							<div class="tips clearfix">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label id="prompt"></label>
							</div>
							<div class="enter">
								<a href="javascript:;" class="purchaser" onClick="javascript:login();return false;">登录</a>
								<a href="javascript:;" class="supplier" onClick="javascript:adminlogin.reset();return false;">取消</a>
							</div>

					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<div class="login-ft">
		<div class="ft-inner">
			<div class="about-us">

			</div>
			<div class="address"></div>
			<div class="other-info"></div>
		</div>
	</div>
	</form>
	<script type=text/javascript>
		var errorMsg='${errorMsg}';
		if (errorMsg != ''&&errorMsg!=null) {
			//$.messager.alert("系统提示", "用户名或密码错误");
			$("#prompt").html("<font color='red'>用户名或密码错误！</font>");
		}
	</script>

</body> 
</html>

<script type="text/javascript">
	function login() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		if (userName == null || userName == "") {
			$("#prompt").html("<font color='red'>用户名不能为空！</font>");
			return;
		}
		if (password == null || password == "") {
			$("#prompt").html("<font color='red'>密码不能为空！</font>");
			return;
		}

		window.location="${pageContext.request.contextPath}/admin/login.do?userName="+userName +"&password="+password;

	}
</script>
