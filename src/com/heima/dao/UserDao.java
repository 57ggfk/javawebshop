package com.heima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.domain.User;
import com.heima.utils.DSUtils;

public class UserDao {

	public static List<User> getBithdayUser(String birthday) throws SQLException {

		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT uid,username,name,email,birthday,gender FROM user WHERE birthday LIKE ?";
		List<User> userList = qr.query(sql, new BeanListHandler<User>(User.class),"%"+birthday+"%");
		
		return userList;
	}

	public static Long getUserCountbyUserName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT COUNT(uid) FROM user WHERE username=?";
		Long count = (Long)qr.query(sql,new ScalarHandler(),username); //count()查询结果是long型的
		return count;
	}

	public static User getUserByLogin(String username, String password) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}

	public static int regUser(User user) throws Exception {

		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[10];
		params[0] = user.getUid();
		params[1] = user.getUsername();
		params[2] = user.getPassword();
		params[3] = user.getName();
		params[4] = user.getEmail();
		params[5] = user.getTelephone();
		params[6] = user.getBirthday();
		params[7] = user.getGender();
		params[8] = user.getState();
		params[9] = user.getCode();
		int update = qr.update(sql,params);
		return update;
	}

	public static String getUserCode(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT code FROM user WHERE uid=?";
		String code = (String)qr.query(sql, new ScalarHandler(),uid);
		return code;
	}
	
	public static int setUserCode(String uid, String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "UPDATE user SET code=? WHERE uid=?";
		int update = qr.update(sql,code,uid);
		return update;
	}

	public static int verifyMailOk(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "UPDATE user SET state=1 WHERE code=?";
		int update = qr.update(sql,code);
		return update;
	}

	public static User getUserById(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DSUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE uid=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),uid);
		return user;
	}

}
