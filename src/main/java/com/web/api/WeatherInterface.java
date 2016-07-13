package com.web.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.web.common.WebUtil;

public class WeatherInterface {
	
	public static final String APIURL = "http://op.juhe.cn/onebox/weather/query";

	public static final String APPKEY = "b5f55cd8851a0b6f8fc4585daa835706";
	
	public static Map<String, String> getData(String cityname, String dtype) {
		StringBuffer sb = new StringBuffer(APIURL);
		sb.append("?key=");
		sb.append(APPKEY);
		sb.append("&dtype=");
		sb.append(dtype);
		sb.append("&cityname=");
		sb.append(cityname);

		String data = WebUtil.getStringByUrl(sb.toString(), 5000);
		System.out.println(data);
//		String area = "N/A";
//		String location = "N/A";
//		
//		if (StringUtils.isNotBlank(data)) {
//			JSONObject jsonObj = new JSONObject(data);
//			area = (String) jsonObj.getJSONObject("result").get("area");
//			location = (String) jsonObj.getJSONObject("result").get("location");
//		}
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("area", area);
//		map.put("location", location);

		return "";
	}
	
	public static void main(String[] args) {
		 java.net.URLEncoder.encode("中国",   "utf-8");  
	}
}
