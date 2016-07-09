package com.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.web.model.User;
import com.web.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		if (WebUtils.getSessionAttribute(request, "user") != null) {
			return "redirect:index";
		}
		User user = loginService.checkLoginInfo(request, response);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return "redirect:index";
		}
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		loginService.loginOutCookie(request, response);
		return "redirect:index";
	}

}
