package com.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class NewsInterface extends CommonApi {

	public static final String APIURL = "http://v.juhe.cn/toutiao/index";
	
	public static final String APPKEY = "cca4b341558ec9de7568d56aceb9cf2b";

	@Override
	public <T> T parseJson(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Test
	public static void main(String[] args) throws IOException {
		//类型:top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "keji");

		Map obj = CommonApi.getJsonMapData(NewsInterface.APIURL, NewsInterface.APPKEY, 5000, map);
		System.out.println(obj);
	}

}
