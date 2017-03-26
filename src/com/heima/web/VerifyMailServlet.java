package com.heima.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.MsgBean;
import com.heima.service.UserService;
import com.heima.utils.StrUtils;

public class VerifyMailServlet extends HttpServlet {
	/**
	 * 接受用户邮箱验证信息链接
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uid = request.getParameter("uid");
		if (!StrUtils.isEmpty(uid)) {
			//如果发来uid，就重新生成code，重新发送验证邮件
			String email = UserService.sendVerifyMail(uid,"http://localhost:8080"+request.getContextPath()+"/verifyMail");
			MsgBean msg = new MsgBean("验证邮件已经发送到："+email,"立即登录邮箱验证","http://mail."+email.split("@")[1]);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		boolean isOk = false;
		String code = request.getParameter("code");
		
		if (!StrUtils.isEmpty(code)) {
			isOk = UserService.verifyMailUrl(code);
		}
		
		if (isOk) {
			MsgBean msg = new MsgBean("恭喜您验证成功！","立即登录",request.getContextPath()+"/login.jsp");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}else {
			MsgBean msg = new MsgBean("抱歉验证失败！请稍后重试","回到首页",request.getContentType()+"/index.jsp");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}