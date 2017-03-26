package com.heima.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.dao.ProductDao;
import com.heima.domain.Product;
import com.heima.utils.CookieUtils;

public class CartService {
	
	public static String addToCart(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String cookieName = "cartIds";		//保存商品id的cookie的名字
		StringBuilder sb = new StringBuilder("");	//保存商品id，最后转存到cookie
		boolean joinCart = false;			//是否加入购物车
		String pid = request.getParameter("pid");
		
		if (pid==null) pid="";
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = CookieUtils.findCookie(cookies, cookieName);
		
		if (cookie == null) {
			//第一次加入购物车
			joinCart = true;
			sb.append(pid);
		} else {
			// 不是第一次加入购物车
			String ids = cookie.getValue();
			sb.append(ids);			//获得原因cookie里存的商品id
			if (ids.contains(pid)) {	
				joinCart = false;	//如果包含此id，则不添加
			} else {
				sb.append(","+pid);
				joinCart = true;	//如果不包含此id，则添加到购物车
			}
		}
		
		if (joinCart) {
			response.addCookie(new Cookie(cookieName,sb.toString()));
		}
		
		return sb.toString();
	}
	
	public static String removeFromCart(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		String cookieName = "cartIds";		//保存商品id的cookie的名字
		Cookie[] cookies = request.getCookies();
		Cookie cookie = CookieUtils.findCookie(cookies, cookieName);
		StringBuilder sb = new StringBuilder("");
		if (cookie!=null) {
			String[] arrStr = cookie.getValue().split(",");
			for (int i=0;i<arrStr.length;i++) {
				if (!arrStr[i].equals(pid)) {
					if (sb.length()<1) {
						sb.append(arrStr[i]);
					} else {
						sb.append(","+arrStr[i]);
					}
				}
			}
		}
		String pids = sb.toString();
		Cookie newCookie = new Cookie(cookieName,pids);
		response.addCookie(newCookie);
		return pids;
	}

	public static void addToCart(String pid) {
		// getProdcutById
		Map<Product,Integer> cart = null;
		
		try {
			Product product = ProductDao.getProduct(pid);
			cart = new HashMap<Product,Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
