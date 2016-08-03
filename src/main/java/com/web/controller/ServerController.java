package com.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.common.MD5Encrypt;
import com.web.model.ListEntry;
import com.web.service.DeployService;
import com.web.service.JsoupYeye;
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
	public String search(String key, String page, ModelMap model) {
		try {
			List<ListEntry> datas = null;
			page = page == null ? "1" : page;
			if(key != null){
				datas = jsoupYeye.getList(key, Integer.parseInt(page));
			}else{
				datas = new ArrayList<ListEntry>();
			}
			int allCount = jsoupYeye.getAllCount();
			if(allCount > 0){
				model.addAttribute("pagination", jsoupYeye.getPagination(allCount, Integer.parseInt(page)));
			}
			jsoupYeye.end();
			model.addAttribute("APPID", "SEARCH");
			model.addAttribute("datas", datas);
			model.addAttribute("allCount", allCount);
			model.addAttribute("currentPage", Integer.parseInt(page));
			model.addAttribute("key", key);
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
			String path = deployService.parseUri(url,"files");
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
	
	@RequestMapping("/showImage/**")
	public void showImage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		OutputStream outStream = null;
		try {
			String url = URLDecoder.decode(request.getRequestURI(), "UTF-8");
			String imagePath = deployService.parseUri(url, "showImage");
			byte[] image = deployService.showImage(imagePath);
			response.setContentType("image/jpeg"); // 设置返回的文件类型
			outStream = response.getOutputStream();
			outStream.write(image); // 输出数据
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(outStream != null){
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
