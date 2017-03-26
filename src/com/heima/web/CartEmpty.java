package com.heima.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.MsgBean;
import com.heima.domain.Product;

public class CartEmpty extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		request.getSession().setAttribute("cart", null);
		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>)request.getSession().getAttribute("cart");
		map.clear();
		//response.sendRedirect("cart.jsp");
		
		MsgBean msg = new MsgBean("购物车已清空!","继续购物",request.getContextPath()+"/index.jsp");
		msg.setContent("您已清空购物车，你可以点击按钮继续购物，或返回购物车查看");
		msg.setBackButton("返回购物车查看");
		msg.setBackUrl(request.getHeader("Referer"));
		msg.setAutoForward(true);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}