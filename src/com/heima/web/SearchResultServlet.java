package com.heima.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Product;
import com.heima.service.ProductSearchService;
import com.heima.service.ProductService;

public class SearchResultServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//前台页面搜索商品，显示到product_list.jsp
		String keyword = request.getParameter("keyword");
		if (request.getMethod().toUpperCase().equals("GET")) {
			/*// (keyword==null?"":keyword) avoid nullpoint exception*/
			keyword = new String((keyword==null?"":keyword).getBytes("iso8859-1"),"utf-8");
		}
		List<Product> list = ProductSearchService.findProductsByName(keyword);
		request.setAttribute("products", list);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}