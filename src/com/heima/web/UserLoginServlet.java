package com.heima.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.MsgBean;
import com.heima.domain.User;
import com.heima.service.UserService;
import com.heima.utils.StrUtils;
import com.heima.utils.VerifyCode;

public class UserLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifycode = request.getParameter("verifycode");
		String autologin = request.getParameter("autologin");
		if (!VerifyCode.match("login_verifycode", verifycode, request, true)) {
			request.setAttribute("msg", "登录失败，验证码不正确");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//			RequestDispatcher dispatcher = request.getRequestDispatcher(request.getHeader("Referer"));
			dispatcher.forward(request, response);
			return;
		} else {
			User user = UserService.getUserByLogin(username,password);
			if (user!=null) {
				//success
				// save user to session
				request.getSession().setAttribute("user", user);
				// set auto login 
				if (!StrUtils.isEmpty(autologin)) { //autologin不为空，就设置自动登录
					
					Cookie cookieUsername = new Cookie("username", user.getUsername());
					Cookie cookiePassword = new Cookie("password",user.getPassword());
					cookieUsername.setMaxAge(7*24*60*60*1000);
					cookiePassword.setMaxAge(7*24*60*60*1000);
					response.addCookie(cookieUsername);
					response.addCookie(cookiePassword);
					
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					return;
				}
				// show success message
				MsgBean msg = new MsgBean();
				msg.setTitle("恭喜您登录成功!");
				msg.setGoButton("去首页");
				msg.setGoUrl(request.getContextPath()+"/index.jsp");
				msg.setBackButton("返回登录前页面");
				msg.setBackUrl(request.getHeader("Referer"));
				request.setAttribute("msg", msg);
//				System.out.println(request.getDispatcherType());
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				//response.getWriter().write("登录成功，5秒后<a href='"+request.getContextPath()+"/index.jsp'>跳转</a>到首页");
				//response.setHeader("Refresh", "5,url='index.jsp'");
			} else {
				//fail
				request.setAttribute("msg", "登录失败，请重新登录");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//				RequestDispatcher dispatcher = request.getRequestDispatcher(request.getHeader("Referer"));
				dispatcher.forward(request, response);		
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}