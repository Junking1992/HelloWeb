package com.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.common.MD5Encrypt;
import com.web.service.DeployService;
import com.web.service.JsoupYeye;
import com.web.service.ListEntry;
import com.web.service.UserInfo;

@Controller
public class ServerController {

	public String key;
	
	public String msg;
	
	public String flag;

	@Autowired
	public JsoupYeye jsoupYeye;
	
	@Autowired
	public DeployService deployService;
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			request.setAttribute("APPID", "SEARCH");
//			key = request.getParameter("key");
//			key = new String(key.getBytes("iso8859-1"), "utf-8");
			List<ListEntry> list = null;
			if(key != null){
				list = jsoupYeye.getList(key);
			}
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
			model.addAttribute("msg", msg);
			model.addAttribute("flag", flag);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		msg = null;
		flag = null;
		return "file_system";
	}
	
	@RequestMapping("/deleteFile/**")
	public String deleteFile(String deleteKey, String path, String fileName){
		try {
			if(!UserInfo.KEY.equals(MD5Encrypt.md5Encode(deleteKey,32))){
				flag = "false";
				msg = "口令错误！！";
				return "redirect:/web/files/"+path;
			}
			boolean isDelete = deployService.deleteFile(path, fileName);
			if(isDelete){
				flag = isDelete+"";
				msg = "删除成功！";
			}else{
				flag = isDelete+"";
				msg = "删除失败！";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/files/"+path;
	}

}
