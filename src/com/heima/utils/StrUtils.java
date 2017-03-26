package com.heima.utils;

public class StrUtils {
	
	/**
	 * 判断字符串是否为空，三种情况： null为空，""为空，"   "也为空
	 * @param str
	 * @return 为空返回true，不为空返回false
	 */
	public static boolean isEmpty(String str) {
		if (str==null||str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 转化为有效字符串: null转化为""，只有空格的转化为空，头尾有空格的删除头尾的空格
	 * @param str
	 *			任意字符串对象，包括null,"","    "," abc ","abc" 				
	 * @return string
	 * 			转换后的字符串
	 */
	public static String toValidString(String str) {
		String string = str;
		if (isEmpty(str)) {
			string = "";
		} else {
			string = str.trim();
		}
		return string;
	}
}
