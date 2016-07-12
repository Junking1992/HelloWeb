package com.web.common;

import java.security.MessageDigest;

public class MD5Encrypt {

	/**
	 * MD5加密 生成16位或32位md5码
	 * 
	 * @param inStr
	 * @param digit
	 * @return
	 * @throws Exception
	 * @author W11821
	 * @date 2016年7月12日 上午10:22:46
	 */
	public static String md5Encode(String inStr, int digit) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = inStr.getBytes("UTF-8");
		//移位
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) (byteArray[i] << 3);
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			if ((md5Bytes[i] & 0xff) < 0x10) {
				hexValue.append("0");
			}
			hexValue.append(Long.toString(md5Bytes[i] & 0xff, 16));
		}
		if (digit == 16) {
			return hexValue.toString().substring(8, 24);
		} else if (digit == 32) {
			return hexValue.toString();
		}
		return "";
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(md5Encode("1234", 32));
	}

}
