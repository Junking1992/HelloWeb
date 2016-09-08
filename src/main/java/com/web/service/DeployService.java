package com.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DeployService {

	public String parseUri(String uri, String word) {
		uri = uri.substring(uri.indexOf(word) + word.length());
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
					list.add("<a href='/web/files/" + file.toString().substring(0, 1) + "'>"
							+ file.toString().substring(0, 1) + " 盘</a>");
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
				if (isImage(file)) {
					list.add("<span>" + file.getName() + "</span><span> -- " + file.length() / 1024
							+ "KB</span><button type='button' class='btn btn-danger btn-xs' style='float:right;' onclick='getFileName(this)' data-toggle='modal' data-target='#myModal'>删除文件</button><button type='button' class='btn btn-success btn-xs' style='float:right;margin-right: 20px;' onclick='showImage(this)' data-toggle='modal' data-target='#imagePage'>打开</button>");
				} else if(isVideo(file)){
					//file.getPath().replace("\\", "/")
					list.add("<span>" + file.getName() + "</span><span> -- " + file.length() / 1024
							+ "KB</span><button type='button' class='btn btn-danger btn-xs' style='float:right;' onclick='getFileName(this)' data-toggle='modal' data-target='#myModal'>删除文件</button><a type='button' class='btn btn-success btn-xs' style='float:right;margin-right: 20px;' href='/web/video/" + file.getPath().replace(":", "").replace("\\", "/")  + "' >播放</a>");
				} else { 
					list.add("<span>" + file.getName() + "</span><span> -- " + file.length() / 1024
							+ "KB</span><button type='button' class='btn btn-danger btn-xs' style='float:right;' onclick='getFileName(this)' data-toggle='modal' data-target='#myModal'>删除文件</button>");
				}
			}
		}
		return list;
	}

	private boolean isImage(File file) {
		String[] imageTypes = UserInfo.IMAGE_TYPE.split(",");
		for (String type : imageTypes) {
			if (file.getName().toUpperCase().endsWith(type)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isVideo(File file) {
		if (file.getName().toUpperCase().endsWith(UserInfo.VIDEO_TYPE)) {
			return true;
		}
		return false;
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

	public boolean deleteFile(String path, String fileName) throws UnsupportedEncodingException {
		path = URLDecoder.decode(path, "UTF-8");
		File file = new File(path.substring(0, 1) + ":" + path.substring(1) + "/" + fileName);
		if (file.isFile()) {
			return file.delete();
		}
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

	public byte[] showImage(String imagePath) {
		FileInputStream fileIs = null;
		try {
			fileIs = new FileInputStream(imagePath.substring(0, 1) + ":" + imagePath.substring(1));
			int i = fileIs.available(); // 得到文件大小
			byte[] data = new byte[i];
			fileIs.read(data); // 读数据
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileIs != null) {
					fileIs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
