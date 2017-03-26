package com.heima.service;

import java.sql.SQLException;

import org.junit.Test;

import com.heima.dao.ProductListDao;

public class TestService {

	public static void main(String[] args) throws SQLException {
		
	}
	
	@Test
	public void testRun() throws SQLException {
		
		Integer integer = ProductListDao.getProductCount("");
		System.out.println(integer);
		System.out.println("获取数量");
	}

}
