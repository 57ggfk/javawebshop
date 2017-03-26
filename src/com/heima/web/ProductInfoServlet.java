package com.heima.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Product;
import com.heima.service.ProductService;

public class ProductInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2205115313506017005L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pid = request.getParameter("pid");
		Product product = ProductService.getProduct(pid);
		request.setAttribute("p", product);
		request.getRequestDispatcher("product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}