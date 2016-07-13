package com.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.web.api.entity.Ip;

public class IpInterface extends CommonApi {

	public IpInterface() {
		this.APIURL = "http://apis.juhe.cn/ip/ip2addr";
		this.APPKEY = "f1fdeb3670d4e6f0a53c192c1f9ca6a5";
	}

	@Override
	public Ip parseJson(JSONObject jsonObj) {
		Ip ip = new Ip();
		ip.setArea((String) jsonObj.getJSONObject("result").get("area"));
		ip.setLocation((String) jsonObj.getJSONObject("result").get("location"));
		return ip;
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ip", "192.168.0.1");
		map.put("dtype", "json");
		System.out.println(new IpInterface().getData(map, 5000));
	}

}
