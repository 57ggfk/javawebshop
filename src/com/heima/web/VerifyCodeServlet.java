package com.heima.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heima.utils.StrUtils;
import com.heima.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153815970993661006L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取参数name将其设置为存储为验证码的session的name值
		String name = request.getParameter("name");
		String value = request.getParameter("value");

		if (!StrUtils.isEmpty(value)) { //如果value不为空（null或""或"  "）
			//传入用户输入的验证码，开始验证是否正确返回{"match":true} or {"match":false}
			if (VerifyCode.match(name, value, request, true)) {
				response.getWriter().write("{\"match\":true}");
			}else {
				response.getWriter().write("{\"match\":false}");
			}
		} else {
			//生成验证码图片，响应给客户端
			try {
				VerifyCode.generateVerifyCode(name,request, response);
			}catch (Exception e) {
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}