package com.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.web.common.WebUtil;

public class IpInterface extends CommonApi {

	public static final String APIURL = "http://apis.juhe.cn/ip/ip2addr";
	
	public static final String APPKEY = "f1fdeb3670d4e6f0a53c192c1f9ca6a5";

	@Override
	public <T> T parseJson(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Map<String,String> getAddress(String ip){
		Map<String,String> args = new HashMap<String, String>();
		args.put("ip", ip);
		args.put("dtype", "json");
		JSONObject obj = CommonApi.getJsonObjData(IpInterface.APIURL, IpInterface.APPKEY, 5000, args);
		args.put("area", obj.getJSONObject("result").getString("area"));
		args.put("location", obj.getJSONObject("result").getString("location"));
		return args;
	}
	
	//Test
	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ip", "127.0.0.1");
		map.put("dtype", "json");

		Map obj = CommonApi.getJsonMapData(IpInterface.APIURL, IpInterface.APPKEY, 5000, map);
		System.out.println(obj);
	}

}
