package com.heima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.heima.domain.Product;
import com.heima.utils.DSUtils;

public class PrductSearchDao {

	public static List<Object> findProductNamesbyName(String keyword) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT pname FROM product WHERE pname LIKE ? limit 0,8";
		List<Object> list = qr.query(sql, new ColumnListHandler(),"%"+keyword+"%");
		
		return list;
	}

	public static List<Product> findProductsByName(String keyword) throws SQLException {

		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pname LIKE ?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),"%"+keyword+"%");
		return list;
	}

}
