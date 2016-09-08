package com.weixin.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.weixin.controller.WeixinController;

public class MyWeiXinEncrypt {

	public static String decoderXML(HttpServletRequest request) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			// 1.从request中取得输入流
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			StringBuffer buf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}
			// 2.解密
			WXBizMsgCrypt wxCeypt = new WXBizMsgCrypt(WeixinController.TOKEN, WeixinController.ENCODINGAESKEY, WeixinController.APPID);
			return wxCeypt.decryptMsg(request.getParameter("msg_signature"), request.getParameter("timestamp"), request.getParameter("nonce"), buf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AesException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static String encoderXML(HttpServletRequest request, String replyMsg) {
		try {
			WXBizMsgCrypt wxCeypt = new WXBizMsgCrypt(WeixinController.TOKEN, WeixinController.ENCODINGAESKEY, WeixinController.APPID);
			return wxCeypt.encryptMsg(replyMsg, request.getParameter("timestamp"), request.getParameter("nonce"));
		} catch (AesException e) {
			e.printStackTrace();
		}
		return "";
	}
}
