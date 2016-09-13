/**  
 * @Title: MD5.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-7-24 上午11:30:17
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-24 上午11:30:17
 */
public class MD5 {
	/**
	 * MD5加密
	 * 
	 * @Title: encrypt
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param plainText
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String encrypt(String plainText) {
		if (plainText == null) {
			return null;
		}
		String result = "";
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("utf-8"));

			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// // System.out.println("result: " + buf.toString());// 32位
			result = buf.toString();// 16位
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(encrypt("123456"));
	}

}
