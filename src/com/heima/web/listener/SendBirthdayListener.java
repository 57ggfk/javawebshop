package com.heima.web.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.heima.domain.User;
import com.heima.service.UserService;
import com.heima.utils.DateTimeUtils;
import com.heima.utils.MailUtils;

/**
 * Application Lifecycle Listener implementation class SendBirthdayListener
 *
 */
public class SendBirthdayListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SendBirthdayListener() {
        
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//定义一个时间格式，用来设置第一执行生日提醒的时间
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	Date firstTime = new Date();
    	
    	try {
    		firstTime = DateTimeUtils.getFirstTime("08:30:00");
    	}catch (ParseException e){
    		System.out.println(e.getMessage());
    	}

    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
			
			private List<User> userList = null;

			@Override
			public void run() {
				//定义一个时间格式，用来获得当前时间的月日
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				String birthday = sdf.format(new Date());
				
				userList  = UserService.getBirthdayUser(birthday);
				if (userList!=null || userList.size()>0) {
					for (User user:userList) {
						String name = user.getName();
						String email = user.getEmail();
						Integer gender = user.getGender(); //1代表男，2代表女，0代表保密
						String appellation = "您好！";//称谓
						if (gender==1) {
							appellation = name + "先生" + appellation;
						} else if (gender==2) {
							appellation = name + "女士" + appellation;
						} else {
							appellation = name + appellation;
						}
						String subject = appellation + "祝您生日快乐！";
						String content = appellation + "今天是您的生日，祝您生日快乐，您可以访问我们的网站了解更多信息<br>"+
										"<a href='http://localhost:8080/heimashop' target='_black'>http://www.heimashop.com</a>";
						SimpleDateFormat sdfLog = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //用于生成日志时间
						try {
							String logSend = sdfLog.format(new Date())+": 给"+name+"："+email+"发送生日邮件";
							System.out.println(logSend);
							MailUtils.sendMail(email, subject, content);		//发送邮件
							String logOk = sdfLog.format(new Date())+": 给"+name+"发送生日邮件成功";
							System.out.println(logOk);
						} catch (Exception e) {
							String logFail = sdfLog.format(new Date())+": 给"+name+"发送生日邮件失败，失败信息："+e.getMessage();
							System.out.println(logFail);
						}
					}
				}
			}
		},firstTime, 1440000);//1*24*60*1000 date过期，依然有效，程序会自动减少第一周期的时间
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }
	
}
