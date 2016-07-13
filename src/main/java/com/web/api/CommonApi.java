package com.web.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.web.common.WebUtil;

abstract class CommonApi {

	public String APIURL;

	public String APPKEY;

	public String getApiString(String api, String key, int timeOut, Map<String, String> map) {
		StringBuffer sb = new StringBuffer(api);
		sb.append("?key=");
		sb.append(key);

		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append("&");
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
		}
		return WebUtil.getStringByUrl(sb.toString(), timeOut);
	}

	public <T> T getData(Map<String, String> args, int timeOut) {
		// 从url获取json字串
		String data = getApiString(APIURL, APPKEY, timeOut, args);
		if(StringUtils.isBlank(data)){
			return null;
		}
		return parseJson(new JSONObject(data));

	}

	public abstract <T> T parseJson(JSONObject jsonObj);

}
