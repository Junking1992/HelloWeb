package com.junking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.junking.model.User;

@Repository
public class UserInfo {
	
	public Map<String,User> getAllUser(){
		Map<String,User> allUser = new HashMap<String,User>();
		allUser.put("wj123", new User("wj123", "1234", "WangJun"));
		allUser.put("wj456", new User("wj456", "1234", "Junking"));
		return allUser;
	}
}
