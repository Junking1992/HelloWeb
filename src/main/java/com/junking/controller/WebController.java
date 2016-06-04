package com.junking.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.junking.model.User;

@Controller
public class WebController {
	
	@Resource
	public User user;
	
	@RequestMapping(value="/login")
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException{
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		if("admin".equals(userName) && "admin".equals(passWord)){
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setName("wangjun");
			session.setAttribute("user", user);
			response.sendRedirect("/jsp/home.jsp");
		}else{
			request.setAttribute("msg", "请输入正确的帐号或密码！");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}
	
}
