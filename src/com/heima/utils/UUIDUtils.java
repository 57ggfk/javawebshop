package com.heima.utils;

import java.util.UUID;
import org.junit.Test;

public class UUIDUtils {
	
	public static String getUUID() {
		
//		UUID randomUUID = UUID.randomUUID();
//		String string = randomUUID.toString();
//		String[] strings = string.split("-");
//		StringBuilder sb = new StringBuilder();
//		for (String str:strings) {
//			sb.append(str);
//		}
//		return sb.toString();
		return UUID.randomUUID().toString().replace("-", "");
		
	}
	
	@Test
	public void testRun() {
		System.out.println(getUUID());
	}
	
}
