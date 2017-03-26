package com.heima.service;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.UserDao;
import com.heima.domain.User;
import com.heima.utils.MailUtils;
import com.heima.utils.StrUtils;
import com.heima.utils.UUIDUtils;

public class UserService {

	public static List<User> getBirthdayUser(String birthday) {

		List<User> userList = null;
		try {
			userList = UserDao.getBithdayUser(birthday);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static boolean userIsExistByUserName(String username) {
		if (StrUtils.isEmpty(username)) {
			return false;
		}
		String string = StrUtils.toValidString(username);
		
		int count = 0;
		try {
			count = (UserDao.getUserCountbyUserName(string)).intValue();
			if (count>0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	public static User getUserByLogin(String username, String password) {
		User user = null;
		try {
			user = UserDao.getUserByLogin(StrUtils.toValidString(username),StrUtils.toValidString(password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 注册新用户，注册成功返回用户的uid
	 * @param user 
	 * @return 注册成功返回uid，注册失败返回null
	 */
	public static String regUser(User user) {
		if (user!=null) {
			user.setUid(UUIDUtils.getUUID());
			user.setCode(UUIDUtils.getUUID()+UUIDUtils.getUUID());
			
			try {
				int i = UserDao.regUser(user);
				if (i>0) {
					return user.getUid();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 给用户发送注册验证邮件，给用户一个链接，点击链接开始验证
	 * @param user
	 * @param url
	 */
	public static String sendVerifyMail(String uid,String url) {
		String mailTitle = "";
		String mailContent = "";
		User user = null;
		try {
			String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
			UserDao.setUserCode(uid, code);
			user = UserDao.getUserById(uid);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return "";
		}
		String name = user.getName();
		if (StrUtils.isEmpty(name)) {
			name = user.getUsername();
		}
		
		mailTitle = "黑马商城恭喜您注册成功";
		mailContent = name+"<h3>您好，黑马商城恭喜您注册成功！您好需要点击下面的链接，激活您的账户<h3>";
		String verifyUrl = url+"?code="+user.getCode();
		mailContent += "<a href="+verifyUrl+" target=''>" + verifyUrl + "</a>";
		
		try {
			MailUtils.sendMail(user.getEmail(), mailTitle, mailContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user.getEmail();
	}
	
	/**
	 * 验证邮件里的链接，验证成功切换用户状态为已验证。
	 * @param uid
	 * @param code
	 * @return
	 */
	public static boolean verifyMailUrl(String code) {
		try {
//			String userCode = UserDao.getUserCode(uid);
			if (code==null) {
				return false;
			}
			int verifyMailOk = UserDao.verifyMailOk(code);
			if (verifyMailOk>0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
