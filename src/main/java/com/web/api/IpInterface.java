package com.web.api;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;

public class IpInterface {
	public static final String APIURL = "http://apis.juhe.cn/ip/ip2addr";
	public static final String APPKEY = "f1fdeb3670d4e6f0a53c192c1f9ca6a5";
	
	public static Map<String,String> getData(String ip, String dtype) throws IOException{
		StringBuffer sb = new StringBuffer(APIURL);
		sb.append("?key=");
		sb.append(APPKEY);
		sb.append("&ip=");
		sb.append(ip);
		sb.append("&dtype=");
		sb.append(dtype);
		String json = Jsoup.connect(sb.toString()).ignoreContentType(true).execute().body();
		JSONObject jsonObj = new JSONObject(json);
		System.out.println(jsonObj);
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		//http://apis.juhe.cn/ip/ip2addr?key=f1fdeb3670d4e6f0a53c192c1f9ca6a5&ip=192.168.0.1&dtype=json
		getData("192.168.0.1","json");
		
	}

}
