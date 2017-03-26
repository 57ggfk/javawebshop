package com.heima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.heima.dao.ProductDao;
import com.heima.domain.Product;

public class ProductService {

	public static Product getProduct(String pid) {
		Product product = null;
		try {
			product = ProductDao.getProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public static List<Product> findProductByMap(Map<String,Object> map){
		List<Product> list = null;
		try {
			list = ProductDao.findProductByMap(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public static boolean editProduct(Product product) {
		try {
			return ProductDao.editProduct(product)==1?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addProduct(Product p) {
		try {
			return ProductDao.addProduct(p)>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean delProdcutById(String pid) {
		int update = 0;
		try {
			update = ProductDao.delProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update>0?true:false;
	}
	
	public static boolean delProductByIds(String[] pids) {
		int update = 0;
		
/*		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<pids.length;i++) {
			if (i==0) {
				sb.append("'"+pids[i]+"'");
			} else {
				sb.append(",").append("'"+pids[i]+"'");
			}
		}
		System.out.println(sb.toString());
		
		try {
			update = ProductDao.delProductByIds(sb.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		if (pids==null || pids.length==0) {
			return false;  //防止空传值报错
		}
		for (String pid:pids) {
			try {
				update += ProductDao.delProductById(pid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("deleted rows:"+update);
		return update>0?true:false;
	}
}
