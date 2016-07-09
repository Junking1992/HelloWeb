package com.web.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie的管理器
 * 
 * @author xiulong.zhang
 * 
 */
public class CookieManager {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public CookieManager(HttpServletRequest req, HttpServletResponse res) {
		request = req;
		response = res;
	}

	/**
	 * 获取远程IP地址 获取客户端真实IP(代理服务器会做代理)
	 * 
	 * @return
	 */
	public String getRemoteIp() {
		return WebUtil.getClientIp(request);
	}

	/**
	 * 设置cookie
	 * 
	 * @param name
	 * @param value
	 * @param domain
	 * @param expire
	 */
	public void setCookie(String name, String value, String domain, int expire) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath("/");
		if (expire >= 0) {
			cookie.setMaxAge(expire);
		}
		response.addCookie(cookie);
	}

	/**
	 * 设置cookie
	 * 
	 * @param name
	 * @param value
	 * @param domain
	 * @param expire
	 */
	public void setNoDomainCookie(String name, String value, int expire) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (expire >= 0) {
			cookie.setMaxAge(expire);
		}
		response.addCookie(cookie);
	}

	/**
	 * 获取某个cookie值
	 *
	 * @param name
	 * @return
	 */
	public String getCookieValue(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equalsIgnoreCase(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
	public void removCookie(String name){
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equalsIgnoreCase(name)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
}
