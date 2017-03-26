package com.heima.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Product;
import com.heima.service.ProductService;

public class CartAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取参数
		String pid = request.getParameter("pid");
		Integer num = 1;
		try {
			num = Integer.valueOf(request.getParameter("num")) ; //转化为Integer方便计算，如果转换失败，使用默认值1			
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 购物车保存到Map中，从Session中取，如果没有就创建一个并保存到Session中
		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>)request.getSession().getAttribute("cart");
		if (map==null) {
			map = new HashMap<Product, Integer>();
			request.getSession().setAttribute("cart", map);
		}
		
		Product product = ProductService.getProduct(pid);
//		System.out.println(product);
		if (map.containsKey(product)) {
			//product已经存在
//			System.out.println("product is exists");
			map.put(product, map.get(product)+num);
		} else {	//product不存在
//			System.out.println("product is not exists");
			map.put(product, num);
		}
//		System.out.println(map);
		
		response.sendRedirect(request.getContextPath()+"/cart_success.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}