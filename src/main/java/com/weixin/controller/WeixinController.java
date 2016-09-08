package com.weixin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.web.common.EncryptUtil;
import com.weixin.security.MyWeiXinEncrypt;

@Controller
public class WeixinController {

	public static final String TOKEN = "Junking";
	public static final String ENCODINGAESKEY = "b8IrveqB4TVYTeKriGb8WwcqIMyNDdgK0Q9Z3dtHhQb";
	public static final String APPID = "wx110a685a82054c59";

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String getServer(String signature, String timestamp, String nonce, String echostr) {
		// 1.将token、timestamp、nonce三个参数进行字典序排序
		List<String> list = new ArrayList<String>();
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);

		// 2.将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str);
		}
		if (EncryptUtil.sha1Encode(sb.toString()).equals(signature)) {
			return echostr;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String postServer(HttpServletRequest request) throws ParserConfigurationException, SAXException, IOException, DocumentException {
		//解密
		String strXml = MyWeiXinEncrypt.decoderXML(request);
		//解析
		Document document =DocumentHelper.parseText(strXml);
		Element root = document.getRootElement();  
		String toUserName = root.element("ToUserName").getText();
		String fromUserName = root.element("FromUserName").getText();
		String content = root.element("Content").getText();
		//新建xml
		Document returnXml = DocumentHelper.createDocument();
		Element returnRoot = returnXml.addElement("xml");
		returnRoot.addElement("ToUserName").addCDATA(fromUserName);
		returnRoot.addElement("FromUserName").addCDATA(toUserName);
		returnRoot.addElement("CreateTime").addCDATA("12345678");
		returnRoot.addElement("MsgType").addCDATA("text");
		returnRoot.addElement("Content").addCDATA("你发送的是："+content);
		//加密
		return MyWeiXinEncrypt.encoderXML(request, returnXml.asXML());
	}
	
}
