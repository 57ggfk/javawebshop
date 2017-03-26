package com.heima.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.heima.domain.Product;
import com.heima.utils.DSUtils;

public class ProductDao {

	public static Product getProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid=?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		try {			
			sql = "UPDATE product SET pflag=pflag+1 WHERE pid=?";
			qr.update(sql,pid);
		} catch (Exception e) {
			
		}
		return product;
	}
	
	public static List<Product> findProductByMap(Map<String,Object> map) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE 1=1 ";
		String keyword = (String) map.get("keyword");
		String is_hot = (String) map.get("is_hot");
		List<Object> list = new ArrayList<Object>();
		if (keyword!=null && !keyword.equals("")) {
			sql += " AND pname LIKE ? ";
			list.add("%"+keyword+"%");
		}
		if (is_hot!=null && !is_hot.equals("")) {
			sql += " AND is_hot=? ";
			list.add(is_hot);
		}
		
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
		
		return query;
	}

	public static int editProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "UPDATE product SET pname=?,market_price=?,shop_price=?,is_hot=?,cid=?,pdesc=? WHERE pid=?";
		Object[] params = {p.getPname(),p.getMarket_price(),p.getShop_price(),p.getIs_hot(),p.getCid(),p.getPdesc(),p.getPid()};
		return qr.update(sql,params);
		
	}

	public static int addProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[10];
		params[0] = p.getPid();
		params[1] = p.getPname();
		params[2] = p.getMarket_price();
		params[3] = p.getShop_price();
		params[4] = p.getPimage();
		params[5] = p.getPdate();
		params[6] = p.getIs_hot();
		params[7] = p.getPdesc();
		params[8] = p.getPflag();
		params[9] = p.getCid();
		int update = qr.update(sql,params);
		return update;
	}

	public static int delProductById(String pid) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "DELETE FROM product WHERE pid=?";
		int update = qr.update(sql,pid);
		
		return update;
	}
	
	// del product by ids string like ('1','2','5','6',) 这种方式太恶心放弃
	public static int delProductByIds(String pids) throws SQLException {
		
		int update = 0;
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "DELETE FROM product WHERE pid IN (?)";
		
//		System.out.println(sql);
		update = qr.update(sql,pids);
		return update;
		
	}
}
