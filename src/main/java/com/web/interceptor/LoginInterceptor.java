package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.web.model.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj) throws Exception {
		User user = (User) WebUtils.getSessionAttribute(httpservletrequest, "user");
		if (user == null) {
			httpservletresponse.sendRedirect(httpservletrequest.getScheme() + "://" + httpservletrequest.getServerName() + ":" + httpservletrequest.getServerPort() + httpservletrequest.getContextPath() + "/web/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj, ModelAndView modelandview) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj, Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

}
