package com.heima.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.heima.domain.Product;
import com.heima.service.ProductService;

public class ProductEditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product product = new Product();
/*		//遍历参数列表
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println(paramName);
		}*/
		//针对日期类型转换，暂时不用
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy/MM/dd"});
		ConvertUtils.register(dateConverter,Date.class);
		
		try {
			BeanUtils.populate(product, parameterMap);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		ProductService.editProduct(product);
		
		response.sendRedirect(getServletContext().getContextPath() + "/admin/product/productFindAll");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}