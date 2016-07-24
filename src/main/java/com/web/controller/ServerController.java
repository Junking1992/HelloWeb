package com.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.service.JsoupYeye;
import com.web.service.ListEntry;

@Controller
public class ServerController {
	
	public String key;
	
	@Autowired
	public JsoupYeye jsoupYeye;
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		try {
			request.setAttribute("APPID", "SEARCH");
			key = request.getParameter("key");
			key = new String(key.getBytes("iso8859-1"),"utf-8");
			List<ListEntry> list = jsoupYeye.getList(key);
			session.setAttribute("list", list);
			session.setAttribute("all", jsoupYeye.getAllCount());
			session.setAttribute("currentPage", 1);
			session.setAttribute("key", key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			return "search2";
		}
	}
	

}
