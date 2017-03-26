package com.heima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.heima.domain.Category;
import com.heima.utils.DSUtils;

public class CategoryDao {

	public static List<Category> getCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		
		String sql = "SELECT * FROM category";
		List<Category> list = qr.query(sql , new BeanListHandler<Category>(Category.class));
		return list;
	}

}
