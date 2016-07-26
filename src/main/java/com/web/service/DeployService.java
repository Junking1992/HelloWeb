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
			return null;
		}
	}

	public List<String> getAllFiles(String path) {
		List<String> list = new ArrayList<String>();
		if (path == null) {
			return list;
		}
		File file = new File(path.substring(0, 1) + ":" + path.substring(1));
		for (File files : file.listFiles()) {
			if (files.isDirectory()) {
				list.add("<a href='/web/files/" + path + "/" + files.getName() + "'>" + files.getName() + "</a>");
			}
		}
		for (File files : file.listFiles()) {
			if (!files.isDirectory()) {
				list.add(files.getName());
			}
		}
		return list;
	}
	
	public List<String> getAllCrumb(String path){
		List<String> list = new ArrayList<String>();
		if (path == null) {
			return list;
		}
		String[] crumbs = path.split("/");
		String url = "/web/files";
		for(int i = 0; i < crumbs.length; i++){
			if(i<crumbs.length-1){
				url += "/" + crumbs[i];
				list.add("<li><a href='" + url + "'>" + crumbs[i] + "</a></li>");
			}else{
				list.add("");
			}
		}
		return list;
	}
}
