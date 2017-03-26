<%@page import="com.heima.service.ProductListService"%>
<%@page import="com.heima.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>黑马商城首页</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
			
		</script>
</head>

<body>
	<div class="container-fluid">

		<!-- 引入header.jsp -->
		<jsp:include page="/header.jsp"></jsp:include>

		<!-- 轮播图 -->
		<div class="container-fluid">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- 轮播图的中的小点 -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<!-- 轮播图的轮播图片 -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="img/1.jpg">
						<div class="carousel-caption">
							<!-- 轮播图上的文字 -->
						</div>
					</div>
					<div class="item">
						<img src="img/2.jpg">
						<div class="carousel-caption">
							<!-- 轮播图上的文字 -->
						</div>
					</div>
					<div class="item">
						<img src="img/3.jpg">
						<div class="carousel-caption">
							<!-- 轮播图上的文字 -->
						</div>
					</div>
				</div>

				<!-- 上一张 下一张按钮 -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>

		<!-- 热门商品 -->
		<div class="container-fluid">
			<div class="col-md-12">
				<h2>
					热门商品&nbsp;&nbsp;<img src="img/title2.jpg" />
				</h2>
			</div>
			<div class="col-md-2 hidden-sm hidden-xs"
				style="border: 1px solid #E7E7E7; border-right: 0; padding: 0;">
				<img src="products/hao/big01.jpg" width="205" height="404"
					style="display: inline-block;" />
			</div>
			<!-- 左边竖条广告 -->
			<div class="col-md-10 col-sm-12">
				<!-- 右边商品列表 -->
				<div class="col-md-6 col-sm-12 col-xs-12"
					style="text-align: center; height: 200px; padding: 0px;">
					<a href="product?pid=1"> <%-- 横条 --%> <img
						src="products/hao/middle01.jpg" width="516px" height="200px"
						style="display: inline-block;">
					</a>
				</div>
				<%
	List<Product> list = ProductListService.getProductsHot();
	pageContext.setAttribute("plist", list);
%>
				<c:forEach items="${plist }" var="p" begin="0" end="9">
					<div class="col-md-2 col-sm-4 col-xs-4"
						style="text-align: center; height: 200px; padding: 10px 0px;">
						<a href="product?pid=${p.pid}"> <img
							src="${pageContext.request.contextPath}/${p.pimage}" width="130"
							height="130" style="display: inline-block;">
						</a>
						<p>
							<a href="product?pid=${p.pid }" style='color: #666'>${p.pname }</a>
						</p>
						<p>
							<font color="#E4393C" style="font-size: 16px">&yen;${p.shop_price }</font>
						</p>
					</div>

				</c:forEach>

			</div>
		</div>

		<!-- 广告条 -->
		<div class="container-fluid">
			<img src="products/hao/ad.jpg" width="100%" />
		</div>

		<!-- 最新商品 -->
		<div class="container-fluid">
			<div class="col-md-12">
				<h2>
					最新商品&nbsp;&nbsp;<img src="img/title2.jpg" />
				</h2>
			</div>
			<div class="col-md-2 hidden-sm hidden-xs"
				style="border: 1px solid #E7E7E7; border-right: 0; padding: 0;">
				<img src="products/hao/big01.jpg" width="205" height="404"
					style="display: inline-block;" />
			</div>
			<div class="col-md-10 col-sm-12">
				<div class="col-md-6 col-sm-12 col-xs-12"
					style="text-align: center; height: 200px; padding: 0px;">
					<a href="product?pid=1"> <img src="products/hao/middle01.jpg"
						width="516px" height="200px" style="display: inline-block;">
					</a>
				</div>
				<%
	List<Product> listNew = ProductListService.getProductsNew();
	pageContext.setAttribute("plistnew",listNew);
%>
				<c:forEach items="${plistnew }" var="p">
					<div class="col-md-2 col-sm-4 col-xs-4"
						style="text-align: center; height: 200px; padding: 10px 0px;">
						<a href="product?pid=${p.pid }"> <img
							src="${pageContext.request.contextPath }/${p.pimage}" width="130"
							height="130" style="display: inline-block;">
						</a>
						<p>
							<a href="product?pid=${p.pid }" style='color: #666'></a>${p.pname }</p>
						<p>
							<font color="#E4393C" style="font-size: 16px">&yen;${p.shop_price }</font>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</div>
</body>

</html>