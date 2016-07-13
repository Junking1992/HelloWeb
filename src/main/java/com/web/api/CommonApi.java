package com.web.api;

import com.web.common.WebUtil;

public class CommonApi {

	public static String getApiString(String api, String key, int timeOut, String... args) {
		StringBuffer sb = new StringBuffer(api);
		sb.append("?key=");
		sb.append(key);
		for (String arg : args) {
			sb.append("&");
			sb.append(arg);
			sb.append("=");
			sb.append(arg);
		}
		System.out.println(sb.toString());
		return WebUtil.getStringByUrl(sb.toString(), timeOut);
	}

}
