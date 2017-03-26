<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
				 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<!-- 页面正文部分 -->
	<div class="container"
		style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-6 col-sm-3 hidden-xs">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5 col-sm-6 col-xs-12">
				<!-- 用户登录面板 -->
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0px 20px 30px; border-radius: 5px; margin: 0 auto; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN
					<div>
						<span style="color:red;font-weight:bold">
							${msg}
							<%--=request.getAttribute("msg")==null?"&nbsp":request.getAttribute("msg") --%>
						</span>
						
					</div>
					<form class="form-horizontal" action="${pageContext.request.contextPath }/userLogin" method="post">
						<div class="form-group">
							<label for="username" class="col-sm-2 col-xs-2 control-label">用户名</label>
							<div class="col-sm-6 col-xs-6">
								<input type="text" class="form-control" id="usernameId" name="username"
									placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="password"
								class="col-sm-2 col-xs-2 control-label">密码</label>
							<div class="col-sm-6 col-xs-6">
								<input type="password" class="form-control" id="passwordId" name="password"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3"
								class="col-sm-2 col-xs-2 control-label">验证码</label>
							<div class="col-sm-3 col-xs-3">
								<input type="text" class="form-control" id="verifycodeId" name="verifycode"
									onkeyup="checkVerifyCode(this)" placeholder="验证码">
							</div>
							<div>
								<a href="javascript:void(0);" style="cursor: pointer; color: blue"
									onclick="changeVerifyCode()">刷新</a> <img
									id="showVerifyCodeId"
									src="${pageContext.request.contextPath }/verifycode?name=login_verifycode"
									onclick="changeVerifyCode()" /> <span id="tipVerifyCodeId"
									style="font-weight: bold"></span>
								<script type="text/javascript">
									function checkVerifyCode(obj) {
										var isOk = false;
										var $tip = $("#tipVerifyCodeId");
										if(obj.value.length==4) {
											var url = "${pageContext.request.contextPath }/verifycode?name=login_verifycode";
											var params = {"value":obj.value.trim()};
											$.post(url,params,function(data){
												if (data.match) {
													$tip.css("color","green");
													$tip.html("验证码输入正确");
												} else {
													$tip.css("color","red");
													$tip.html("验证码输入错误");
												}
											},'json');
										} else {
											$tip.html("");
											return;
										}
									}
									
									function changeVerifyCode() {
										var src = "${pageContext.request.contextPath }/verifycode?name=login_verifycode&"+Math.random();
										document.getElementById("showVerifyCodeId").src=src;
									}
								</script>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" name="autologin" value="1"> 自动登录
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
										type="checkbox" name="rememberusername" value="1"> 记住用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- 用户登录面板 结束-->
			<div class="col-md-1 col-sm-3 hidden-xs">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>
		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>