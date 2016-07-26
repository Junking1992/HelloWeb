package com.web.service;

import java.io.File;

public class DeployService {
	
	public static void main(String[] args) {
		File file = new File("D:/");
		String[] s = file.list();
		for(String str : s){
			System.out.println(str);
		}
	}
}
