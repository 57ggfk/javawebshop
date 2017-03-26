package com.heima.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Category;
import com.heima.domain.Product;
import com.heima.service.CategoryService;
import com.heima.service.ProductService;

public class ProductEditUIServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取商品信息，发送到request域
		String pid = request.getParameter("pid");
		
		Product product = ProductService.getProduct(pid);
		request.setAttribute("p", product);
		//获取商品类别新学年，发送到request域
		List<Category> categories = CategoryService.getCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}