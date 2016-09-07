package com.weixin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.common.EncryptUtil;

@Controller
public class WeixinController {
	
	private static final String TOKEN = "Junking";
	
	@ResponseBody 
	@RequestMapping(method = RequestMethod.GET)
	public String testServer(String signature, String timestamp, String nonce, String echostr){
		//1.将token、timestamp、nonce三个参数进行字典序排序
		List<String> list = new ArrayList<String>();
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		
		//2.将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuffer sb = new StringBuffer();
		for(String str : list){
			sb.append(str);
		}
		if(EncryptUtil.sha1Encode(sb.toString()).equals(signature)){
			return echostr;
		}
		return null;
	}
	
	
	
}
