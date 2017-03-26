<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function addProduct(){
		window.location.href = "${pageContext.request.contextPath}/admin/product/productAddUI";
	}
	
	function selectAll() {
		var checkedText = "全不选"; //定义变量只能是var
		var uncheckText = "全选择";
		var $obj = $("#selectAllId"); //jquery定义变量 var $变量
		if ($obj.attr('name')=='1') {  // attr和prop用法
			$obj.attr('value',checkedText);
			$obj.attr('name','0');
			$(":checkbox[name=pids]").prop('checked','checked'); //checked与selected区别
		} else {
			$obj.prop('value',uncheckText);
			$(":checkbox[name=pids]").prop('checked','');
			$obj.attr('name','1');
		}
	}
	
	function selectReverse() {
		$(":checkbox[name=pids]").click(); //反选就是把所有复选框点一遍
	}
	
	function selectRemove() {
		/*var pids = "";
		var $objs = $(":checkbox[name=pids]:checked");
		$objs.each(function(index,ele){
			
		});*/
		var $objs = $(":checkbox[name='pids']:checked");
		if ($objs.size()<1){
			alert("请选择要删除的项目");
			return false;
		}
		if (!confirm("此操作不可恢复，确认要删除所选吗？")) {
			return false;
		}
		//以提交表单的方法提交参数
		$("#frmId").attr("action","${pageContext.request.contextPath}/admin/product/delmulti");
		$("#frmId").attr("method","post");
		$("#frmId")[0].submit(); //js方法
	}
	
	function search() {
		$("#frmId").attr("action","${pageContext.request.contextPath }/admin/product/search");
		$("#frmId").attr("method","post");
		$('#frmId').submit();//jquery事件，没有on有()
	}
	
	
	$(function(){
		// 自动热门
		$("#isHotId option[value='${is_hot}']").prop("selected",true);
	});
</script>
</HEAD>
<body>
	<br>
	<form id="frmId" name="frmName" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01">
						<table width=100%>
							<tr>
								<td align="left" width="25%"><input id="selectAllId"
									name="1" type="button" value="全选择" onclick="selectAll()">
									<input id="selectReverseId" name="" type="button" value="反选"
									onclick="selectReverse()"> <input id="selsectRemoveId"
									name="" type="button" value="删除选择" onclick="selectRemove()">
								</td>
								<td style="text-align: center;" align="center" width="50%">

									商品名称：<input type="text" name="keyword" value="${keyword }"
									placeholder="请输入查询关键字" /> 是否热门：<select id="isHotId"
									name="is_hot">
										<option value="">--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
								</select> <input type="button" value="搜索" onclick="search()">
								</td>
								<td width="25%" align="center">
									<button type="button" id="add" name="add" value="添加"
										class="button_add" onclick="addProduct()">
										&#28155;&#21152;</button>

								</td>
							</tr>
						</table>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="8%">多选删除</td>
								<td align="center" width="10%">序号</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>

							<c:forEach items="${requestScope.products }" var="p"
								varStatus="state">

								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; height: 22px" align="center"
										width="8%"><input name="pids" type="checkbox"
										value="${p.pid }" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${state.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><a
										href="${pageContext.request.contextPath }/product?pid=${p.pid}"
										target="_blank"> <img width="40" height="45"
											src="${pageContext.request.contextPath }/${p.pimage }"></a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><a
										href="${pageContext.request.contextPath }/product?pid=${p.pid}"
										target="_blank"> ${p.pname }</a></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${p.shop_price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${p.is_hot==1?"是":"否" }</td>
									<td align="center" style="HEIGHT: 22px"><a
										href="${ pageContext.request.contextPath }/admin/product/productEditUI?pid=${p.pid}">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath }/admin/product/del?pid=${p.pid}"
										onclick="return confirm('您确认要删除吗');"> <img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>

