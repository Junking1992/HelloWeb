package com.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.web.common.WebUtil;

/**
 * Api通用
 * @author W11821
 *
 */
public abstract class CommonApi {

	/**
	 * 通过API得到JsonObject数据
	 * 
	 * @param apiUrl
	 * @param appKey
	 * @param timeOut
	 * @param args
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:25:10
	 */
	public static JSONObject getJsonObjData(String apiUrl, String appKey, int timeOut, Map<String, String> args) {
		// 从url获取json字串
		String data = getApiString(apiUrl, appKey, timeOut, args);
		if (StringUtils.isBlank(data)) {
			return null;
		}
		return new JSONObject(data);

	}

	/**
	 * 通过API得到Map数据
	 * 
	 * @param apiUrl
	 * @param appKey
	 * @param timeOut
	 * @param args
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:25:36
	 */
	public static Map<String, Object> getJsonMapData(String apiUrl, String appKey, int timeOut, Map<String, String> args) {
		// 从url获取json字串
		String data = getApiString(apiUrl, appKey, timeOut, args);
		if (StringUtils.isBlank(data)) {
			return null;
		}
		return jsonToMap(new JSONObject(data));

	}

	/**
	 * 通过API得到自定义数据对象（需实现抽象方法-parseJson(JSONObject jsonObj)）
	 * 
	 * @param apiUrl
	 * @param appKey
	 * @param timeOut
	 * @param args
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:35:57
	 */
	public <T> T getObjData(String apiUrl, String appKey, int timeOut, Map<String, String> args) {
		// 从url获取json字串
		String data = getApiString(apiUrl, appKey, timeOut, args);
		if (StringUtils.isBlank(data)) {
			return null;
		}
		return parseJson(new JSONObject(data));

	}

	/**
	 * 通过API获取文本
	 * 
	 * @param api
	 * @param key
	 * @param timeOut
	 * @param map
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:38:03
	 */
	public static String getApiString(String api, String key, int timeOut, Map<String, String> map) {
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

	/**
	 * 将JSONObject转成Map
	 * 
	 * @param jsonObj
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:23:48
	 */
	public static Map<String, Object> jsonToMap(JSONObject jsonObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<String> keySet = jsonObj.keySet();
		for (String s : keySet) {
			Object obj = jsonObj.get(s);
			if (obj instanceof JSONObject) {
				map.put(s, jsonToMap((JSONObject) jsonObj.get(s)));
			} else if (obj instanceof JSONArray) {
				JSONArray array = (JSONArray) obj;
				List<Object> list = new ArrayList<Object>();
				for (int i = 0; i < array.length(); i++) {
					if (array.get(i) instanceof String) {
						list.add((String) (array.get(i)));
					} else {
						list.add(jsonToMap((JSONObject) array.get(i)));
					}
				}
				map.put(s, list);
			} else if (obj.getClass().toString().equals("class org.json.JSONObject$Null")) {
				map.put(s, null);
			} else if (obj instanceof Integer) {
				map.put(s, ((Integer) obj).toString());
			} else {
				map.put(s, (String) obj);
			}
		}
		return map;
	}

	/**
	 * 解析成自定义Bean对象
	 * 
	 * @param jsonObj
	 * @return
	 * @author W11821
	 * @date 2016年7月14日 下午3:39:47
	 */
	public abstract <T> T parseJson(JSONObject jsonObj);

}
