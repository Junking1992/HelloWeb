package com.junking.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.junking.controller.UserInfo;
import com.junking.model.User;

@Service
public class LoginService {
	
	@Resource
	public UserInfo userInfo;

	public User checkLoginInfo(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		User user = userInfo.getAllUser().get(userName);
		if(user != null && user.getPassWord().equals(passWord)){
			return user;
		}
		return null;
	}

}
