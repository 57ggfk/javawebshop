<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:if test="${msg.autoForward }">
<meta http-equiv="Refresh" content="10;url=${requestScope.msg.goUrl }">
</c:if>
<title>黑马商城购物车</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function (){
		if (!${msg.autoForward }) {
			return false;
		}
		var second = 10;
		var interval = setInterval(function(){
			showMsg = "提示信息：本页<span style='color:red:font-weight:bold'>"+second+"</span>秒后自动跳转";
			$("#autoCloseTipId").html(showMsg);
			second-=1;
			if (second==0) {
				clearInterval(interval);
				$("#autoCloseTipId").html("信息展示：");
			}
		},1000);
	});
</script>
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
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath }">首页</a></li>
				<li class="active" id="autoCloseTipId">信息展示</li>
			</ol>
		</div>
		<div class="row">
			<div class="jumbotron">

				<h2>${requestScope.msg.title }</h2>
				<p>${requestScope.msg.content }</p>
				<p>
					<c:if test="${not empty msg.goButton || not empty msg.goUrl }">
					<a class="btn btn-primary btn-lg" href="${requestScope.msg.goUrl }" role="button">${requestScope.msg.goButton }</a>
					</c:if> 
					<c:if test="${not empty msg.backUrl || not empty msg.backButton }">
					<a href="${requestScope.msg.backUrl }" class="btn btn-default btn-lg" role="button">${requestScope.msg.backButton }</a>
					</c:if>
				</p>
			</div>
		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>