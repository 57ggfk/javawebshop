package com.heima.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyCode {
	
	/**
	 * 生成验证码图片，响应给客户端，保存验证码字符串到session域里的name属性里
	 * @param name	指定要保存到session里的属性名
	 * @param req	HttpServletRequest
	 * @param resp	HttpServletResponse
	 * @return 返回验证码字符串  
	 * @throws IOException
	 */
	public static String generateVerifyCode(String name,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int width = 60;
		int height = 30;
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		String strcode = ""; 
		
		// create a image
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
		// get a graphic
		Graphics g = image.getGraphics();
		// padding a rect ,the first set color
		g.setColor(Color.BLACK);
		g.fillRect(0,0,width, height);
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, width-2, height-2);
		// set font
		g.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,25));
		// type random words
		for (int i=0;i<4;i++) {
			// set color random
			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			// get random words
			int index = random.nextInt(data.length());
			String str = data.substring(index, index + 1);
			// write 
			g.drawString(str, width/6 * (i+1), 20);
			strcode = strcode + str;
		}
		// lines
		for (int i=0;i<3;i++) {
			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
			g.drawOval(random.nextInt(width), random.nextInt(height), 2, 2);
		}
		// save strcode to session if specify name
		if (name!=null&&!name.trim().equals("")) {
			HttpSession session = req.getSession();
			session.setAttribute(name, strcode);
		}
		// end response image to browser
		ImageIO.write(image, "jpg", resp.getOutputStream());
		return strcode;
		
	}
	
	/**
	 * session域里指定属性的值与用户输入的值是否匹配，用户校验验证码是否匹配
	 * @param name		用户指定session域中的属性名
	 * @param veirfycode	用户输入的验证码字符串
	 * @param request	用于从request对象获取session域
	 * @param ignoreCase 忽略大小写
	 * @return		匹配返回true，不匹配返回false
	 */
	public static boolean match(String name,String verifycode,HttpServletRequest request, boolean ignoreCase) {
		
		if (name==null||name.trim().equals("")) {
			return false;
		}
		
		HttpSession session = request.getSession();
		String attribute = (String)session.getAttribute(name);
		
		if (attribute==null||attribute.trim().equals("")) {
			return false;
		}
		
		if (verifycode.trim().equals(attribute.trim())) {
			return true;
		} else if(ignoreCase)	{
			return verifycode.trim().equalsIgnoreCase(attribute.trim());
		} else {
			return false;
		}
	}
}
