package com.heima.service;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.CategoryDao;
import com.heima.domain.Category;

public class CategoryService {
	
	public static List<Category> getCategory() {
		List<Category> list = null;
		try {
			list = CategoryDao.getCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
