package com.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.web.model.User;


@Scope("singleton")
@Service("allUser")
public class UserInfo {
	
	public Map<String,User> getAllUser(){
		Map<String,User> allUser = new HashMap<String,User>();
		allUser.put("wangjun", new User("wangjun", "1234", "WangJun"));
		allUser.put("junking", new User("junking", "1234", "Junking"));
		return allUser;
	}
}
