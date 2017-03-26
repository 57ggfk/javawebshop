package com.heima.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.MsgBean;

public class UserExitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.getSession().setAttribute("user", null);
		request.getSession().invalidate();
		// clear cookie 
		Cookie cookie = new Cookie("username","");
		Cookie cookiePassword = new Cookie("password","");
		cookie.setMaxAge(0);
		cookiePassword.setMaxAge(0);
		response.addCookie(cookie);
		response.addCookie(cookiePassword);
		//response.sendRedirect(request.getHeader("Referer"));
		MsgBean msg = new MsgBean("已退出登录!","回到首页",request.getContextPath()+"/index.jsp");
		msg.setAutoForward(true);
		msg.setContent("您已经退出登录，你可以点击按钮回到首页，或返回之前页面，稍后自动跳转到首页");
		msg.setBackButton("返回之前页");
		msg.setBackUrl(request.getHeader("Referer"));
		request.setAttribute("msg", msg);
		//System.out.println(request.getDispatcherType());
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}