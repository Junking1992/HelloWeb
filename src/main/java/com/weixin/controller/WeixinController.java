package com.weixin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.weixin.service.BasicAutoReply;

@Controller
public class WeixinController {

	@Autowired
	private BasicAutoReply basicAutoReply;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String getServer(HttpServletRequest request) {
		return basicAutoReply.authentication(request);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String postServer(HttpServletRequest request) throws ParserConfigurationException, SAXException, IOException, DocumentException {
		return basicAutoReply.reply(request);
	}
	
}
