package com.web.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DeployService {

	public String parseUri(String uri) {
		uri = uri.substring(uri.indexOf("files") + 5);
		if (StringUtils.isNotBlank(uri)) {
			String path = uri.substring(1);
			return path;
		} else {
			return "";
		}
	}

	public List<String> getAllFiles(String path) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isBlank(path)) {
			File[] files = File.listRoots();
			for (File file : files) {
				if (file.isDirectory()) {
					list.add("<a href='/web/files/" + file.toString().substring(0, 1) + "'>" + file.toString().substring(0, 1) + " 盘</a>");
				}
			}
			return list;
		}
		File files = new File(path.substring(0, 1) + ":/" + path.substring(1));
		for (File file : files.listFiles()) {
			if (file.isDirectory()) {
				list.add("<a href='/web/files/" + path + "/" + file.getName() + "'>" + file.getName() + "</a>");
			}
		}
		for (File file : files.listFiles()) {
			if (!file.isDirectory()) {
				list.add("<span>" + file.getName() + "</span><button type='button' class='btn btn-danger btn-xs' style='float:right;' onclick='getFileName(this)' data-toggle='modal' data-target='#myModal'>删除文件</button>");
			}
		}
		return list;
	}

	public List<String> getAllCrumb(String path) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isBlank(path)) {
			list.add("<li class='active'>本地磁盘</a></li>");
			return list;
		}
		String[] crumbs = path.split("/");
		String url = "/web/files";
		list.add("<li><a href='" + url + "'>本地磁盘</a></li>");
		for (int i = 0; i < crumbs.length; i++) {
			if (i < crumbs.length - 1) {
				url += "/" + crumbs[i];
				list.add("<li><a href='" + url + "'>" + crumbs[i] + "</a></li>");
			} else {
				list.add("<li class='active'>" + crumbs[i] + "</a></li>");
			}
		}
		return list;
	}
	
	public boolean deleteFile(String path, String name){
		
		return false;
	}
	
	public static void main(String[] args) {
		File files = new File("D:");
		for (File file : files.listFiles()) {
			System.out.println(file);
			if (file.isDirectory()) {
			}
		}
	}
	
}
