package com.heima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestRun {
	public static void main(String[] args){
		SimpleDateFormat sdfDateTimeM = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hhmmss");
//		SimpleDateFormat sdfTimeM = new SimpleDateFormat("hh:mm:ss a");
////		System.out.println(sdfDateTimeM.format(setDate));
		
/*		long time3 = System.currentTimeMillis();
		for (int i=0;i<10000000;i++) {
			getNextTime("11:30:00");
			getNextTime("21:30:00");
		}
		long time4 = System.currentTimeMillis();
		System.out.println(time4-time3);*/
		try {
			System.out.println(sdfTime.parse("080000").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
