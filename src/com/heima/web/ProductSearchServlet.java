package com.heima.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Product;
import com.heima.service.ProductService;

public class ProductSearchServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String keyword = request.getParameter("keyword");
		String is_hot = request.getParameter("is_hot");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("is_hot", is_hot);
		
		List<Product> list = ProductService.findProductByMap(map);
		request.setAttribute("products", list);
		request.setAttribute("keyword", keyword);
		request.setAttribute("is_hot", is_hot);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}