package com.heima.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.DbUtils;

import com.heima.domain.Product;
import com.heima.service.ProductService;
import com.heima.utils.ImageUtils;
import com.heima.utils.UUIDUtils;

public class ProductAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product product = new Product();
		//针对日期类型转换，暂时不用
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy/MM/dd"});
		ConvertUtils.register(dateConverter,Date.class);
		// 数据封装
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 生成pid
		product.setPid(UUIDUtils.getUUID());
		// 获取当前时间，并添加到product中
		product.setPdate(new Date());
		// 默认图片
		product.setPimage(ImageUtils.getProductImage());
		
		boolean isOk = ProductService.addProduct(product);
		if (isOk) {
			response.sendRedirect(getServletContext().getContextPath()+"/admin/product/productFindAll");
		} else {
			response.sendRedirect(getServletContext().getContextPath()+"/admin/product/productAddUI");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}