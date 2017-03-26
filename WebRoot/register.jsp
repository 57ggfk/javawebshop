<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
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

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;" action="${pageContext.request.contextPath }/userRegister" method="POST">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名" onblur="existVerify(this)"
								onfocus="$('#userIsExistTipId').slideUp(200)">
							<div id="userIsExistTipId"></div>
						</div>
						<script type="text/javascript">
							var $tip = $("#userIsExistTipId");
							function existVerify(obj) {
								if (obj.value.trim()=="") return false;
								url = "${pageContext.request.contextPath}/userIsExist";
								params = {"username":$("#username").val()};
								$.post(url,params,function(data){
									if(data.isExist == true) {
										$tip.html("<font style='color:red;font-weight: bold'>此用户名已被占用</font>");
										$tip.slideDown(200);
									} else {
										$tip.html("<font style='color:green;font-weight: bold'>此用户名可用</font>");
										$tip.slideDown(200);
									}
								},"json");
							}
							
						</script>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="inputPassword3" 
								name="password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd" 
								name="password2" placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3" 
								name="email" placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption" 
								name="name" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="inlineRadio1" value="1">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" id="inlineRadio2" value="2">
								女
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" id="inlineRadio0" value="0" checked="checked" >
								保密
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday" >
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 col-xs-12 control-label">验证码</label>
						<div class="col-sm-3 col-xs-6">
							<input id="verifycodeId" name="verifycode" type="text"
								class="form-control"><label></label>
						</div>
						<div class="col-sm-4 col-xs-6">
							<img id="showVerifyCodeId"
								src="${pageContext.request.contextPath }/verifycode?name=register_verifycode"
								onclick="changeVerifyCode()" /> <a href="javascript:void(0);"
								onclick="changeVerifyCode()">刷新</a>
							<script type="text/javascript">
								function changeVerifyCode() {
									$("#showVerifyCodeId").attr("src","${pageContext.request.contextPath }/verifycode?name=register_verifycode&pic"+Math.random());
								}
							</script>
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




