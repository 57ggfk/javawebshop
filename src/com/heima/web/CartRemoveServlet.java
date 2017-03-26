package com.heima.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.Product;
import com.heima.service.CartService;

public class CartRemoveServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		String pids = CartService.removeFromCart(request, response);
		String pid = request.getParameter("pid");
//		System.out.println(pid);
		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>)request.getSession().getAttribute("cart");
		if (map!=null) {
			Set<Entry<Product, Integer>> entrySet = map.entrySet();
			Iterator<Entry<Product, Integer>> iterator = entrySet.iterator();
			while(iterator.hasNext()) {
				Entry<Product, Integer> next = iterator.next();
				if (next.getKey().getPid().equals(pid)) {
					iterator.remove();	// 如果pid一致，就删除当前元素
					break;
				}
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}