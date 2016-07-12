package com.junking.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junking.model.User;
import com.junking.service.JsoupYeye;
import com.junking.service.ListEntry;
import com.junking.service.LoginService;

@Controller
public class WebController {
	
	@Resource
	public LoginService loginService;
	@Resource
	public JsoupYeye jsoup;
	
	public String key;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException{
		User user = loginService.checkLoginInfo(request);
		if(user != null){
			session.setAttribute("user", user);
			response.sendRedirect("home");
		}else{
			request.setAttribute("msg", "请输入正确的帐号或密码！");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		User user = (User) session.getAttribute("user");
		if(user != null){
			return "redirect:home";
//			response.sendRedirect("home");
		}else{
			return "redirect:home";
//			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
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
	
	@RequestMapping(value="/search")
	public String search(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		key = request.getParameter("key");
		key = new String(key.getBytes("iso8859-1"),"utf-8");
		List<ListEntry> list = jsoup.getList(key);
		session.setAttribute("list", list);
		session.setAttribute("all", jsoup.getAllCount());
		session.setAttribute("currentPage", 1);
		session.setAttribute("key", key);
		return "search";
	}
	
	@RequestMapping(value="/goInfo")
	public void  goInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String url = request.getParameter("url");
		if(!"".equals(url)){
			url = jsoup.parseInfoPage(url);
		}
		response.sendRedirect(url);
	}
	
	@RequestMapping(value="/page")
	public String  page(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String urlKey = jsoup.getUrlKey();
		int where = Integer.parseInt(request.getParameter("where"));
		List<ListEntry> list = jsoup.getList(urlKey,where);
		session.setAttribute("list", list);
		session.setAttribute("all", jsoup.getAllCount());
		session.setAttribute("currentPage", where);
		session.setAttribute("key", key);
		return "search";
	}
	
	@RequestMapping(value="/add")
	public String  add(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String urlKey = jsoup.getUrlKey();
		int where = Integer.parseInt(request.getParameter("where"));
		List<ListEntry> list = jsoup.getList(urlKey,where);
		session.setAttribute("list", list);
		session.setAttribute("all", jsoup.getAllCount());
		session.setAttribute("currentPage", where);
		session.setAttribute("key", key);
		return "search";
	}
	
}
