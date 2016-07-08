package com.web.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.web.model.User;


@Service
public class LoginService {
	
	@Resource
	public UserInfo allUser;

	public User checkLoginInfo(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		User user = allUser.getAllUser().get(userName);
		if(user != null && user.getPassWord().equals(passWord)){
			return user;
		}
		return null;
	}

}
