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
		allUser.put("wangjun", new User("wangjun", "80e6597a4d5de68e645ff9f53c0ec0f4", "WangJun"));
		allUser.put("junking", new User("junking", "3b47518b23baced9c660d7620a876a5d", "Junking"));
		return allUser;
	}
}