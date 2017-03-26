package com.heima.service;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.ProductListDao;
import com.heima.domain.Product;

public class ProductListService {

	public static List<Product> getProducts(String cid) {
		List<Product> list = null;
		try {
			list = ProductListDao.getProducts(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getProductAll() {
		List<Product> list = null;
		try {
			list = ProductListDao.getProducts(null); //cid为空，获取所有产品
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getProductsByIds(String pids) {
		List<Product> list = null;
		try {
			list = ProductListDao.getProductsByIds(pids);
		} catch (Exception e) {
			
		}
		return list;
	}
	
	public static List<Product> getProductsHot() {
		String sql = "SELECT * FROM product WHERE is_hot=1 LIMIT 9";
		List<Product> list = null;
		try {
			 list = ProductListDao.getProductsBySql(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getProductsNew() {
		
		String sql = "SELECT * FROM product ORDER BY pdate DESC limit 9";	
		List<Product> list = null;
		try {
			list = ProductListDao.getProductsBySql(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static Integer getProductCount(String cid) {
		Integer count = 0;
		if (cid==null || cid.equals("")) { // 如果没有传入cid则查询所有类别的商品
			try {
				count = ProductListDao.getProductCount();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				count = ProductListDao.getProductCount(cid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}

	public static List<Product> getProductsByPage(String cid, Integer startIndex, Integer pageSize) {
		List<Product> list = null;
		if (cid==null || cid.equals("")) {	// 如果没有传入cid则查询所有类别的商品
			try {
				list = ProductListDao.getProductsByPage(startIndex,pageSize);
			} catch (Exception e) {
				
			}
		} else {
			try {
				list = ProductListDao.getProductByPage(cid,startIndex,pageSize);
			} catch (Exception e) {
				
			}
		}
		
		return list;
	}

}
