package com.web.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.web.api.entity.Weather;

public class WeatherInterface extends CommonApi {

	public WeatherInterface() {
		this.APIURL = "http://op.juhe.cn/onebox/weather/query";
		this.APPKEY = "b5f55cd8851a0b6f8fc4585daa835706";
	}

	@Override
	public Weather parseJson(JSONObject jsonObj) {
		Weather weather = new Weather();
		weather.setReason(jsonObj.getString("reason"));
		return weather;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cityname", java.net.URLEncoder.encode("武汉", "utf-8"));
		map.put("dtype", "json");
		System.out.println(new WeatherInterface().getData(map, 5000));
	}

}
