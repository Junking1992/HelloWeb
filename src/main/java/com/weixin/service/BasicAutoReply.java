package com.weixin.service;

import org.springframework.stereotype.Component;

import com.weixin.controller.WeixinController;
import com.weixin.main.AutoReply;

@Component
public class BasicAutoReply extends AutoReply {

	@Override
	public void init() {
		TOKEN = WeixinController.TOKEN;
		ENCODINGAESKEY = WeixinController.ENCODINGAESKEY;
		APPID = WeixinController.APPID;
	}

	@Override
	public String doText(String content) {
		return text("Yes!" + content);
	}

	@Override
	public String doImage(String picUrl, String mediaId) {
		return image(mediaId);
	}

	@Override
	public String doVoice(String mediaId, String format) {
		return voice(mediaId);
	}

	@Override
	public String doVideo(String mediaId, String thumbMediaId) {
		return image(thumbMediaId);
	}

}
