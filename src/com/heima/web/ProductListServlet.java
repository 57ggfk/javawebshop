package com.heima.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.PageBean;
import com.heima.domain.Product;
import com.heima.service.ProductListService;

public class ProductListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3926752823579048265L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取参数，商品列表编号cid
		String cid = request.getParameter("cid");
		String page = request.getParameter("page");
		//准备分页参数
		Integer pageNumber = 1;
		if (page!=null&&!"".equals(page)) pageNumber = Integer.valueOf(page);
		Integer pageSize = 12;
		Integer totalRecord = ProductListService.getProductCount(cid);
		
		PageBean<Product> pageBean = new PageBean<Product>(pageNumber, pageSize, totalRecord);
		// 从数据库查询数据 SELECT * FROM produt WHERER cid=类别编号cid LIMIT 起始索引startIndex,每页个数pageSize
		Integer startIndex = pageBean.getStartIndex();
		List<Product> list = ProductListService.getProductsByPage(cid,startIndex,pageSize);
		pageBean.setData(list);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("products", list);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}