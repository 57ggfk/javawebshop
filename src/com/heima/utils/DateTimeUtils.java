package com.heima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * 
 * @author Administrator
 *
 */
public class DateTimeUtils {

	final static long DAY_VALUE = 1 * 24 * 60 * 60 * 1000;

//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdfDateTimeM = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//		// SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		// SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
//		// SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
//		// SimpleDateFormat sdfTimeM = new SimpleDateFormat("hh:mm:ss a");
//		
//		Date nextTime = getFirstTime("19:30:00");
//		System.out.println(sdfDateTimeM.format(nextTime));
//		Date nextTime2 = getFirstTime("23:32:00");
//		System.out.println(sdfDateTimeM.format(nextTime2));
//		testEffect();
//	}
//
//	private static void testEffect() throws ParseException {
//		int len = 100000;
//		long time1 = System.currentTimeMillis();
//		for (int i = 0; i < len; i++) {
//			getFirstTime("11:30:00");
//			getFirstTime("23:30:00");
//		}
//		long time2 = System.currentTimeMillis();
//		System.out.println(time2 - time1);
//		long time3 = System.currentTimeMillis();
//		for (int i = 0; i < len; i++) {
//			getFirstTime1("11:30:00");
//			getFirstTime1("23:30:00");
//		}
//		long time4 = System.currentTimeMillis();
//		System.out.println(time4 - time3);
//		long time5 = System.currentTimeMillis();
//		for (int i = 0; i < len; i++) {
//			getFirstTime2("11:30:00");
//			getFirstTime2("23:30:00");
//		}
//		long time6 = System.currentTimeMillis();
//		System.out.println(time6 - time5);
//	}
	
	/**
	 * 获取第一次到达指定时分秒的时间点
	 * @param hhmmss 参数格式"hh:mm:ss"，支持24小时制，如"23:30:30"
	 * @return 返回一个Date对象，表示将来要到达的时间点
	 * @throws ParseException
	 */
	public static Date getFirstTime(String hhmmss) throws ParseException {

		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
		// 指定时间hhmmss，用户指定的字符串hh:mm:ss支持24小时制，识别

		Date newTime = sdfTime.parse(hhmmss);
	
		long willLong = newTime.getTime();

		// 获取当前时区偏移量（毫秒值）中国时区+8偏移量是8*60*60*1000=28800000
		int timezoneOffset = Calendar.getInstance().getTimeZone().getOffset(0);

		long onedayLong = 1000 * 60 * 60 * 24;
		long nowLong = new Date().getTime(); // 今天的日期+时间
		long passLong = (nowLong - timezoneOffset) % onedayLong; // 今天的时间，不包括日期
		long todayLong = nowLong - passLong; // 今天的日期，不包括时间
		todayLong = ((nowLong - timezoneOffset) / onedayLong * onedayLong);

		long setLong;
		if (passLong > willLong) {
			setLong = todayLong + onedayLong + willLong;
		} else {
			setLong = todayLong + willLong;
		}

		return new Date(setLong); // 第一个时间
	}
	
	/**
	 * 
	 * 获取第一次到达指定时分秒的时间点
	 * @param hhmmss 参数格式"hh:mm:ss"，支持24小时制，如"23:30:30"
	 * @return 返回一个Date对象，表示将来要到达的时间点
	 * @throws ParseException
	 * @deprecated effect is low 效率太低，已过时请使用getFirstTime(String hhmmss)
	 */
	public static Date getFirstTime1(String hhmmss) throws ParseException {
		// SimpleDateFormat sdfDateTimeM = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss a");
		// SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat sdfTimeM = new SimpleDateFormat("hh:mm:ss a");

		// 当前日期时间
		Date current = new Date();

		// 当前时间，不包含日期，即1970-1-1
		Date currentTime = sdfTimeM.parse(sdfTimeM.format(current));
		long currentTimeValue = currentTime.getTime();

		// 当前日期，不包含时间，即00:00:00
		String currentDateStr = sdfDate.format(current);
		Date currentDate = sdfDate.parse(currentDateStr);
		long currentDateValue = currentDate.getTime();

		// 指定时间hhmmss，用户指定的字符串hh:mm:ss支持24小时制，识别
		Date newTime = sdfTime.parse(hhmmss);
		long newTimeValue = newTime.getTime();

		if (currentTimeValue > newTimeValue) {
			// 代表已经过期，返回第二天的时间
			// System.out.println("时间已过期，返回明天的指定时间");
			return new Date(currentDateValue + newTimeValue + DAY_VALUE);
		} else {
			// 代表未过期
			// System.out.println("时间未过期，返回今天的指定时间");
			return new Date(currentDateValue + newTimeValue);

		}
	}

	/**
	 * 
	 * 获取第一次到达指定时分秒的时间点
	 * @param hhmmss 参数格式"hh:mm:ss"，支持24小时制，如"23:30:30"
	 * @return 返回一个Date对象，表示将来要到达的时间点
	 * @throws ParseException
	 * @deprecated effect is low 效率太低，已过时请使用getFirstTime(String hhmmss)
	 */
	public static Date getFirstTime2(String hhmmss) throws ParseException {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat sdfTimeM = new SimpleDateFormat("hh:mm:ss a");

		// 当前日期时间
		Date current = new Date();

		// 当前时间，不包含日期，即1970-1-1
		Date currentTime = sdfTimeM.parse(sdfTimeM.format(current));
		long currentTimeValue = currentTime.getTime();

		// 当前日期，不包含时间，即00:00:00
		String currentDateStr = sdfDate.format(current);
		Date currentDate = sdfDate.parse(currentDateStr);
		long currentDateValue = currentDate.getTime();
		// 明天日期
		String tomorrowDateStr = sdfDate.format(currentDateValue + DAY_VALUE);

		// 指定时间hhmmss，用户指定的字符串hh:mm:ss支持24小时制，识别
		Date newTime = sdfTime.parse(hhmmss);
		long newTimeValue = newTime.getTime();

		if (currentTimeValue > newTimeValue) {
			// 代表已经过期，返回第二天的时间
			// System.out.println("时间已过期，返回明天的指定时间");
			return sdfDateTime.parse(tomorrowDateStr + " " + hhmmss);
		} else {
			// 代表未过期
			// System.out.println("时间未过期，返回今天的指定时间");
			return sdfDateTime.parse(currentDateStr + " " + hhmmss);
		}
	}

	public long getInterval(String hhmmss) {
		return 0;
	}
}
