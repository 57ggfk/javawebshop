package com.heima.service;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.PrductSearchDao;
import com.heima.domain.Product;

public class ProductSearchService {

	public static List<Object> findProductNamesByName(String keyword) {
		if (keyword==null||"".equals(keyword.trim())) {
			return null;
		}
		
		List<Object> list = null;
		try {
			list = PrductSearchDao.findProductNamesbyName(keyword.trim());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Product> findProductsByName(String keyword) {
		
		if (keyword==null||"".equals(keyword.trim())) {
			return null;
		}
		
		List<Product> list = null;
		try {
			list = com.heima.dao.PrductSearchDao.findProductsByName(keyword.trim());
		} catch (Exception e) {
			
		}
		return list;
	}

}
