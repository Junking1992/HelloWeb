package com.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.service.DeployService;
import com.web.service.JsoupYeye;
import com.web.service.ListEntry;

@Controller
public class ServerController {

	public String key;

	@Autowired
	public JsoupYeye jsoupYeye;
	
	@Autowired
	public DeployService deployService;
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			request.setAttribute("APPID", "SEARCH");
			key = request.getParameter("key");
			key = new String(key.getBytes("iso8859-1"), "utf-8");
			List<ListEntry> list = jsoupYeye.getList(key);
			session.setAttribute("list", list);
			session.setAttribute("all", jsoupYeye.getAllCount());
			session.setAttribute("currentPage", 1);
			session.setAttribute("key", key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "search2";
	}

	@RequestMapping("/files/**")
	public String files(HttpServletRequest request, ModelMap model) {
		try {
			String url = URLDecoder.decode(request.getRequestURI(), "UTF-8");
			String path = deployService.parseUri(url);
			model.addAttribute("files", deployService.getAllFiles(path));
			model.addAttribute("crumbs", deployService.getAllCrumb(path));
			model.addAttribute("path", path);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "file_system";
	}
	
	@RequestMapping("/deleteFile/**")
	public String deleteFile(String deleteKey, String path, String fileName, ModelMap model){
		try {
			boolean flag = deployService.deleteFile(path, fileName);
			if(flag){
				model.addAttribute("msg", "删除成功！");
			}else{
				model.addAttribute("msg", "删除失败！");
			}
		} catch (UnsupportedEncodingException e) {
			model.addAttribute("msg", "删除失败！");
			e.printStackTrace();
		}
		return "redirect:/web/files/"+path;
	}

}
