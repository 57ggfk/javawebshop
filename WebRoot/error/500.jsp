<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page isErrorPage="true"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<HEAD>
<TITLE>提醒您：系统错误</TITLE>
<META content="MSHTML 6.00.2800.1458" name=GENERATOR>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}

TABLE {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}

TD {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}

BODY {
	SCROLLBAR-HIGHLIGHT-COLOR: buttonface;
	SCROLLBAR-SHADOW-COLOR: buttonface;
	SCROLLBAR-3DLIGHT-COLOR: buttonhighlight;
	SCROLLBAR-TRACK-COLOR: #eeeeee;
	BACKGROUND-COLOR: #ffffff
}

A {
	FONT-SIZE: 9pt;
	COLOR: #842b00;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: none
}

A:hover {
	FONT-SIZE: 9pt;
	COLOR: #0188d2;
	LINE-HEIGHT: 16pt;
	FONT-FAMILY: "Tahoma", "宋体";
	TEXT-DECORATION: underline
}

H1 {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Tahoma", "宋体"
}
</STYLE>
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function showErrorMessage(){
	    $("#errorMessage").toggle();
	}
</script>
</HEAD>
<BODY topMargin=20>
	<TABLE cellSpacing=0 width=700 align=center border=0 cepadding="0">
		<TBODY>
			<TR colspan="2">
				<TD vAlign=top align=middle><IMG height=100
					src="${pageContext.request.contextPath}/images/404.jpg" width=100
					border=0>
					<TD>
						<TD>
							<!--------System Return Begin------------>
							<H1>系统错误</H1> HTTP 错误 500：<font color="red"><%=exception.getMessage() %></font>
							<HR noShade SIZE=0>

							<P>☉ 请尝试以下操作：</P>
							<UL>
								<LI>确保您的操作正确无误。
									<LI>如果您的操作正确无误，请与网站管理员联系，通知他们检查系统问题。
										<LI>单击<A href="javascript:history.back(1)"><FONT
												color=#ff0000>后退</FONT></A>按钮返回一上一页。
									</LI>
										<LI><A href="javascript:showErrorMessage()"><FONT
												color=#ff0000>显示全部错误</FONT></A></LI>
							</UL>
							<P>
								☉ 如果您对本站有任何疑问、意见、建议、咨询，请联系管理员</A> <BR>&nbsp;&nbsp;&nbsp;<BR>
							</P>
							<p id="errorMessage"
								style="display: none; width: 600px; font-size: 18px">
								<c:forEach var="trace"
									items="${pageContext.exception.stackTrace}">
							${trace}
						</c:forEach>
							</P> <!------------End this!--------------->
					</TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>