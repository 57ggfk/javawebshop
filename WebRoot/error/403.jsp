<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<HEAD>
<TITLE>提醒您：您没有本操作的权限</TITLE>
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

<META content="MSHTML 6.00.2800.1458" name=GENERATOR>
</HEAD>
<BODY topMargin=20>
	<TABLE cellSpacing=0 width=600 align=center border=0 cepadding="0">
		<TBODY>
			<TR colspan="2">
				<TD vAlign=top align=middle><IMG height=100
					src="${ctx}/static/images/404.jpg" width=100 border=0>
					<TD>
						<TD>
							<!--------System Return Begin------------>
							<H1>无法找到该页</H1> HTTP 错误 403：您可能没有正在访问的页面的权限。
							<HR noShade SIZE=0>

							<P>☉ 请尝试以下操作：</P>
							<UL>
								<LI>确保您拥有对应的权限。
									<LI>如果您拥有对应的权限，请与网站管理员联系，通知他们检查系统问题。
										<LI>单击<A href="javascript:history.back(1)"><FONT
												color=#ff0000>后退</FONT></A>按钮尝试另一个链接。
									</LI>
							</UL>
							<P>
								☉ 如果您对本站有任何疑问、意见、建议、咨询，请联系管理员</A> <BR>&nbsp;&nbsp;&nbsp;<BR>
							</P> <!------------End this!--------------->
					</TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>