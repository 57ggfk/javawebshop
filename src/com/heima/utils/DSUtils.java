package com.heima.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DSUtils {
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static {
		//
	}
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	// get Current Connection for transaction
	public static Connection getCurrentConnection() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			conn = getConnection();
			tl.set(conn);
		}
		return conn;
	}
	
	public static void startTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);
	}
	
	public static void rollback() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.rollback();
	}
	
	public static void commit() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.commit();
	}
	
	public static void close(ResultSet rs,Statement stmt,Connection conn){

		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				stmt = null;
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}	

	}
}
