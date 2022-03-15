package com.example.springbootshunfengfengqiao.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class Md5AndBase64Util {

	public static String md5EncryptAndBase64(String str) {
		return encodeBase64(md5Encrypt(str));
	}

	private static byte[] md5Encrypt(String encryptStr) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(encryptStr.getBytes("utf8"));
			return md5.digest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String encodeBase64(byte[] b) {
		BASE64Encoder base64Encode = new BASE64Encoder();
		String str = base64Encode.encode(b);
		return str;
	}

}