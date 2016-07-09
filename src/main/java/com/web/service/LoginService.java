package com.web.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.web.common.CookieManager;
import com.web.common.DateUtils;
import com.web.model.User;

@Service
public class LoginService {

	private final String COOKIE_NAME = "JunCookie";

	private User user;

	private CookieManager manager;

	@Resource
	public UserInfo allUser;

	/**
	 * 登录密码验证
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author W11821
	 * @date 2016年7月9日 下午3:40:40
	 */
	public User checkLoginInfo(HttpServletRequest request, HttpServletResponse response) {
		manager = new CookieManager(request, response);
		String cookieValue = manager.getCookieValue(COOKIE_NAME);
		if (cookieValue == null || !checkCookieValue(cookieValue)) {
			if (checkRequestIsBlank(request)) {
				return null;
			}else{
				checkUserNameAndPassWord(request);
			}
		}
		return user;
	}

	/**
	 * 验证帐号密码，并存入cookie保存至当天24点
	 * 
	 * @param request
	 * @author W11821
	 * @date 2016年7月9日 下午3:40:06
	 */
	private void checkUserNameAndPassWord(HttpServletRequest request) {
		String userName = request.getParameter("userName").trim();
		String passWord = request.getParameter("passWord").trim();
		if (checkPassWord(userName, passWord)) {
			// 设定cookie有效时间为从登录到当天24点
			String nowDayStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");// 获取当前日期
			Date nowDayEndStr = DateUtils.parseDate(nowDayStr + " 23:59:59", "yyyy-MM-dd HH:mm:ss");// 当天最后1秒时间
			Integer cookieTimeOut = Long.valueOf((nowDayEndStr.getTime() - new Date().getTime()) / 1000).intValue();// 到24点的秒数

			// 加密组成cookieValue
			String cookieValue = userName + "-" + passWord;

			manager.setNoDomainCookie(COOKIE_NAME, cookieValue, cookieTimeOut);
		}
	}

	/**
	 * 检查请求是不是空的
	 * 
	 * @param request
	 * @return
	 * @author W11821
	 * @date 2016年7月9日 下午3:17:44
	 */
	private boolean checkRequestIsBlank(HttpServletRequest request) {
		String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName").trim();
		String passWord = request.getParameter("passWord") == null ? "" : request.getParameter("passWord").trim();
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查cookieValue合不合法
	 * 
	 * @param cookieValue
	 * @return
	 * @author W11821
	 * @date 2016年7月9日 下午3:18:05
	 */
	private boolean checkCookieValue(String cookieValue) {
		String[] values = cookieValue.split("-");
		if (values.length < 2) {
			return false;
		}
		// 解密相关
		return checkPassWord(values[0], values[1]);
	}

	/**
	 * 检查帐号密码是否正确
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 * @author W11821
	 * @date 2016年7月9日 下午3:18:45
	 */
	private boolean checkPassWord(String userName, String passWord) {
		user = allUser.getAllUser().get(userName);
		if (user != null && user.getPassWord().equals(passWord)) {
			return true;
		} else {
			user = null;
		}
		return false;
	}

	public void loginOutCookie(HttpServletRequest request, HttpServletResponse response) {
		manager = new CookieManager(request, response);
		manager.removCookie(COOKIE_NAME);
	}
}
