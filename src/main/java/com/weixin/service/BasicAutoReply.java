package com.weixin.service;

import org.springframework.stereotype.Component;

import com.weixin.main.AutoReply;

@Component
public class BasicAutoReply extends AutoReply {

	@Override
	public void init() {
		TOKEN = "Junking";
		ENCODINGAESKEY = "b8IrveqB4TVYTeKriGb8WwcqIMyNDdgK0Q9Z3dtHhQb";
		APPID = "wx110a685a82054c59";
		SECRET = "4f58d5bc1e01ea2e957e90d6a5c2acb1";
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
		return video(mediaId, "", "");
	}

	@Override
	public String doShortvideo(String mediaId, String thumbMediaId) {
		return image(thumbMediaId);
	}

	@Override
	public String doLocation(String location_X, String location_Y, String scale, String label) {
		return text("location_X:" + location_X + "\nlocation_Y:" + location_Y + "\nscale:" + scale + "\nlabel:" + label);
	}

	@Override
	public String doLink(String title, String description, String url) {
		return text("title:" + title + "\ndescription:" + description + "\nurl:" + url);
	}

}
