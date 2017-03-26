package com.heima.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heima.service.ProductSearchService;

public class SearchListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String keyword = request.getParameter("keyword");
		if (request.getMethod().toUpperCase().equals("GET")) {
			// if request method is get
			System.out.println("if request method is get");
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
		}
//		System.out.println(keyword);
		List<Object> list = ProductSearchService.findProductNamesByName(keyword);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}