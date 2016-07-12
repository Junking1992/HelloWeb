package com.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.web.model.User;
import com.web.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (WebUtils.getSessionAttribute(request, "user") != null) {
			return "redirect:index";
		}
		User user = null;
		try {
			user = loginService.checkLoginInfo(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return "redirect:index";
		}
		model.put("msg", loginService.msg);
		return "login";
	}

	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "index";
	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		loginService.loginOutCookie(request, response);
		return "redirect:login";
	}

}
