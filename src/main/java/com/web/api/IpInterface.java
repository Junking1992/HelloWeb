package com.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class IpInterface {

	public static final String APIURL = "http://apis.juhe.cn/ip/ip2addr";

	public static final String APPKEY = "f1fdeb3670d4e6f0a53c192c1f9ca6a5";

	public static Map<String, String> getData(String ip, String dtype) {
		
		String data = CommonApi.getApiString(APIURL, APPKEY, 5000, ip, dtype);
		
		String area = "N/A";
		String location = "N/A";
		
		if (StringUtils.isNotBlank(data)) {
			JSONObject jsonObj = new JSONObject(data);
			area = (String) jsonObj.getJSONObject("result").get("area");
			location = (String) jsonObj.getJSONObject("result").get("location");
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("area", area);
		map.put("location", location);

		return map;
	}

	public static void main(String[] args) throws IOException {
		// http://apis.juhe.cn/ip/ip2addr?key=f1fdeb3670d4e6f0a53c192c1f9ca6a5&ip=192.168.0.1&dtype=json
		System.out.println(getData("192.168.0.1", "json"));
	}

}
