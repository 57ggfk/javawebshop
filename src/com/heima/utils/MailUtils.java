package com.heima.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	
	// 0.设置初始化参数
	public static String protocol = "SMTP";
	public static String host = "localhost";
	public static String username = "aaa";
	public static String password = "123";
	public static String from = "aaa@localhost";
	public static String isAuth = "true";
	public static Properties config = new Properties();
	static {
		
		try {
			// 从配置文件mail.properies载入初始化参数
			config.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));
			protocol = config.getProperty("protocol");
			host = config.getProperty("host");
			username = config.getProperty("username");
			password = config.getProperty("password");
			from = config.getProperty("from");
			isAuth = config.getProperty("isAuth");

		} catch (IOException e) {
			System.out.println("配置文件载入失败");
		}
	}
	
	public static void sendMail(String emailAddress, String subject,String emailMsg)
			throws AddressException, MessagingException, Exception {
		
		
		// 1.创建一个程序与邮件服务器会话对象 Session
		
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", protocol);
		props.setProperty("mail.host", host);
		props.setProperty("mail.smtp.auth", isAuth);// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		
		// 设置发件人:
		message.setFrom(new InternetAddress(from)); // 设置发送者
		if (emailAddress==null||emailAddress.trim().equals("")) emailAddress = "xxx@xxx.xxx"; //预定义一个不存在的邮箱，防止空异常
		
		// 设置收件人:
		// TO:收件人（主要针对的对象） CC：抄送人（通知） BCC：暗送/密送
		message.setRecipient(RecipientType.TO, new InternetAddress(emailAddress)); // 设置发送方式与接收者

		message.setSubject(subject);
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}

}
