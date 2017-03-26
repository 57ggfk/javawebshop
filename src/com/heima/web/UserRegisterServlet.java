package com.heima.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.heima.domain.MsgBean;
import com.heima.domain.User;
import com.heima.service.UserService;
import com.heima.utils.StrUtils;

public class UserRegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			DateConverter dateConvert = new DateConverter();
			dateConvert.setPatterns(new String[]{"yyyy-MM-dd","yyyy/MM-dd"});
			ConvertUtils.register(dateConvert, Date.class);
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		String uid = UserService.regUser(user);
		if (!StrUtils.isEmpty(uid)) {
			//发送注册验证邮件
			UserService.sendVerifyMail(uid,"http://localhost:8080"+request.getContextPath()+"/verifyMail");
			//注册成功
			MsgBean msg = new MsgBean();
			msg.setAutoForward(true);
			msg.setTitle("恭喜您注册成功!");
			msg.setGoButton("请登录");
			msg.setGoUrl(request.getContextPath()+"/login.jsp");
			msg.setBackButton("返回首页");
			msg.setBackUrl(request.getContextPath()+"/index.jsp");
			request.setAttribute("msg", msg);
		} else {
			//注册失败
			MsgBean msg = new MsgBean();
			msg.setTitle("抱歉注册失败!");
			msg.setGoButton("去首页");
			msg.setGoUrl(request.getContextPath()+"/index.jsp");
			msg.setBackButton("返回重新注册");
			msg.setBackUrl(request.getHeader("Referer"));
			request.setAttribute("msg", msg);
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}