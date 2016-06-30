package com.junking.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junking.model.User;
import com.junking.service.LoginService;

@Controller
public class WebController {
	
	@Resource
	public LoginService loginService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException{
		User user = loginService.checkLoginInfo(request);
		if(user != null){
			session.setAttribute("user", user);
			response.sendRedirect("home");
		}else{
			request.setAttribute("msg", "��������ȷ���ʺŻ����룡");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		User user = (User) session.getAttribute("user");
		if(user != null){
			response.sendRedirect("home");
		}else{
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}
	
	@RequestMapping(value="/home")
	public void home(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		User user = (User) session.getAttribute("user");
		if(user != null){
			request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		}else{
			response.sendRedirect("login");
		}
	}
	
	@RequestMapping(value="/exit")
	public void exit(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		session.setAttribute("user", null);
		response.sendRedirect("login");
	}
	
}
