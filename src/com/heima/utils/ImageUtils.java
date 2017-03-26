package com.heima.utils;

import java.util.Random;
import org.junit.Test;

public class ImageUtils {
	
	public static String getProductImage() {
		// 从 products/1/c_0001.jpg 到 products/1/c_0050.jpg
		// 重点 0001-0050
		StringBuilder sb = new StringBuilder("products/1/c_");
		Random random = new Random();
		int nextInt = random.nextInt(51);
		int len = String.valueOf(nextInt).length();
		for (int i=0;i<4-len;i++) {
			sb.append("0");
		}
		sb.append(String.valueOf(nextInt));
		sb.append(".jpg");
		return sb.toString();
	}
	
	@Test
	public void testRun() {
		System.out.println(getProductImage());
	}
	
}
