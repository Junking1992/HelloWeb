package com.web.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class WeatherInterface extends CommonApi {

	public static final String APIURL = "http://op.juhe.cn/onebox/weather/query";
	
	public static final String APPKEY = "b5f55cd8851a0b6f8fc4585daa835706";

	@Override
	public <T> T parseJson(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Test
	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cityname", java.net.URLEncoder.encode("武汉", "utf-8"));
		map.put("dtype", "json");
		
		Map obj = CommonApi.getJsonMapData(WeatherInterface.APIURL, WeatherInterface.APPKEY, 5000, map);
		System.out.println(obj);
	}

}
