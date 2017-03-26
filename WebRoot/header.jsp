<%@page import="com.heima.domain.Category"%>
<%@page import="com.heima.service.CategoryService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4 col-sm-6">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5 hidden-sm hidden-xs">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3 col-sm-6" style="padding-top: 20px">
		<ol class="list-inline">
			<c:if test="${empty user }"><%-- 用户没有登录 --%>
			<li><a href="${pageContext.request.contextPath }/login.jsp">登录</a></li>
			<li><a href="${pageContext.request.contextPath }/register.jsp">注册</a></li>
			<li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/order_list.jsp">我的订单</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li><a href="${pageContext.request.contextPath }/index.jsp">欢迎 ${empty user.name?user.username:user.name }</a></li>
			<li><a href="${pageContext.request.contextPath }/userExit">退出</a></li>
			<li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/order_list.jsp">我的订单</a></li>
			</c:if>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="productList?cid=">所有商品<span
							class="sr-only">(current)</span></a></li>
					<%	
						List<Category> list = null;
						try {
							list = (List<Category>)session.getAttribute("menulist");
						} catch (Exception e) {}
						
						if (list==null) {
							list = CategoryService.getCategory();
							session.setAttribute("menulist", list);
						}
						
						pageContext.setAttribute("clist", list);
					%>
					<c:forEach items="${clist}" var="c">
						<li><a href="productList?cid=${c.cid }">${c.cname }</a></li>
					</c:forEach>
				</ul>
				<form class="navbar-form navbar-right" role="search"
					id="frmSearchId" method="get" action="searchResult">
					<div class="form-group" style="position: relative;">
						<input id="searchInputId" type="text" name="keyword"
							class="form-control" placeholder="请输入要搜索的内容"
							onkeydown="if(event.keyCode==13) frmSearchId.submit()"
							onkeyup="searchlist(this)">
						<div id="searchlistId"
							style="border: solid 1px #1D67AD; background-color: #fff; position: absolute; width: 196px; display: none; z-index: 999">
						</div>

					</div>
					<button type="submit" class="btn btn-default" id="btnSearchId">搜索</button>
				</form>
				<script type="text/javascript">
					function searchlist(o) {
						if (o.value==null||o.value.trim()=="") {
							$("#searchlistId").html("");
							//$("#searchlistId").hide();
							$("#searchlistId").slideUp(200);
							return false;
						}
						var content = "";
						var keyword = o.value.trim();
						$.post("searchList",{"keyword":keyword},function(data){
							// [{"pid":"xxxxx","pname","yyy"},{"pid":"xxxxx","pname":"yyy"}]
							// [{"pname":"xxx"},{"pname":"yyy"},{"pname":"zzz"}]
							if (data==null) return;
							for (var i=0;i<data.length;i++) {
								var htm = highLight(data[i],keyword);
								content += "<div style='padding:4px;cursor:pointer' onmouseover='addColor(this)' onmouseout='removeColor(this)' onclick='typein(this)'>"+htm+"</div>";
							}
							//console.log(content);
							$("#searchlistId").html(content);
							//$("#searchlistId").show();
							$("#searchlistId").slideDown(200);
						},"json");
						
					}
					
					function addColor(o) {
						$(o).css('background-color','#338FFF');
						
					}
					
					function removeColor(o) {
						$(o).css('background-color','#fff');
					}
					
					function typein(o){
						$("#searchInputId").val(o.innerText);
						//$("#searchlistId").hide();
						$("#searchlistId").slideUp(200);
					}
					
					function highLight(content,keyword) {
						return content.replace(keyword,"<font color='red'>"+keyword+"</font>");
					}
				</script>
			</div>
		</div>
	</nav>
</div>