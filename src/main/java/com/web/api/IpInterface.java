package com.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class IpInterface extends CommonApi {

	public static final String APIURL = "http://apis.juhe.cn/ip/ip2addr";
	
	public static final String APPKEY = "f1fdeb3670d4e6f0a53c192c1f9ca6a5";

	@Override
	public <T> T parseJson(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Test
	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ip", "192.168.0.1");
		map.put("dtype", "json");

		Map obj = CommonApi.getJsonMapData(APIURL, APPKEY, 5000, map);
		System.out.println(obj);
	}

}
