package com.heima.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.domain.Product;
import com.heima.utils.DSUtils;

public class ProductListDao {

	public static List<Product> getProducts(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE cid=? limit 12";
		List<Product> list = null;
		if(cid==null || "".equals(cid.trim())) {
			sql = "SELECT * FROM product";
			list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		}else {
			list = qr.query(sql, new BeanListHandler<Product>(Product.class),cid); 
		}

		return list;
	}

	public static List<Product> getProductsByIds(String pids) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid in (" + pids + ")";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
	
	public static List<Product> getProductsBySql(String sql) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public static Integer getProductCount() throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product";
		// 服务器返回的结果是Long型的
		Long count = (Long)qr.query(sql, new ScalarHandler());
		return count.intValue();
	}

	public static Integer getProductCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product";
		List<Object> params = new ArrayList<Object>();
		if (cid!=null&&!"".equals(cid)) {
			sql = sql + " WHERE cid=?";
			params.add(cid);
		}
		// 服务器返回的结果是Long型的
		Long count = (Long) qr.query(sql, new ScalarHandler(),params.toArray());
		return count.intValue();
	}

	public static List<Product> getProductsByPage(Integer startIndex, Integer pageSize) throws SQLException {

		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM product LIMIT ?,?";
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
		return query;
		
	}

	public static List<Product> getProductByPage(String cid, Integer startIndex, Integer pageSize) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		List<Object> params = new ArrayList<Object>();
		String sql = "SELECT * FROM product ";
		
		if (cid!=null && !cid.equals("")) { //易错点!cid.equals("")记住是不为空串
			sql = sql + " WHERE cid=? ";
			params.add(cid);
		}
		
		if (startIndex!=null & pageSize!=null) {
			sql = sql + " LIMIT ?,? ";
			params.add(startIndex);
			params.add(pageSize);
		}
		
//		System.out.println(cid+":"+sql);
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class),params.toArray());
		return query;
		
	}
	
}
