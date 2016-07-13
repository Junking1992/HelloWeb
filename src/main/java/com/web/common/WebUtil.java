package com.web.common;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class WebUtil
{
  private static final String QUERYSTRING_PREFIX = "?";
  private static final String HOSTNAME_UNKNOWN = "unknown";
  private static final String EQUAL_STRING = "=";
  private static final String LEFT_BRACKET = "[";
  private static final String RIGHT_BRACKET = "]";
  private static final String COMMA_STRING = ",";
  private static final String EMPTY_STRING = "";
  
  public static String getRequestFullUrl(HttpServletRequest httpServletRequest)
  {
    String returnValue = null;
    StringBuilder sb = new StringBuilder();
    StringBuffer requestURL = httpServletRequest.getRequestURL();
    sb.append(requestURL);
    String queryString = httpServletRequest.getQueryString();
    if (StringUtils.isNotBlank(queryString)) {
      sb.append("?").append(queryString);
    }
    returnValue = sb.toString();
    return returnValue;
  }
  
  public static String getClientIp(HttpServletRequest httpServletRequest)
  {
    String returnValue = null;
    returnValue = httpServletRequest.getHeader(ProxyClientIPHttpHeaderNameEnum.XFORWARDEDFOR.getHeaderName());
    if ((StringUtils.isBlank(returnValue)) || ("unknown".equalsIgnoreCase(returnValue))) {
      returnValue = httpServletRequest.getHeader(ProxyClientIPHttpHeaderNameEnum.PROXYCLIENTIP.getHeaderName());
    }
    if ((StringUtils.isBlank(returnValue)) || ("unknown".equalsIgnoreCase(returnValue))) {
      returnValue = httpServletRequest.getHeader(ProxyClientIPHttpHeaderNameEnum.WLPROXYCLIENTIP.getHeaderName());
    }
    if ((StringUtils.isBlank(returnValue)) || ("unknown".equalsIgnoreCase(returnValue))) {
      returnValue = httpServletRequest.getRemoteAddr();
    }
    if (null != returnValue)
    {
      String[] ips = returnValue.split(",");
      for (String oneIp : ips) {
        if (!"unknown".equals(oneIp))
        {
          returnValue = oneIp;
          break;
        }
      }
    }
    return returnValue.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":returnValue;
  }
  
  public static String getRequestHeadersInfo(HttpServletRequest httpServletRequest)
  {
    String returnValue = null;
    Enumeration headerNames = httpServletRequest.getHeaderNames();
    StringBuilder sb = new StringBuilder();
    sb.append("Request Headers:");
    sb.append("[");
    if (null != headerNames)
    {
      Object headerName = null;
      String headerValue = null;
      while (headerNames.hasMoreElements())
      {
        headerName = headerNames.nextElement();
        headerValue = httpServletRequest.getHeader(headerName.toString());
        
        sb.append(headerName).append("=").append(headerValue).append(headerNames.hasMoreElements() ? "," : "");
      }
    }
    sb.append("]");
    returnValue = sb.toString();
    return returnValue;
  }
  
  public static String getRequestParamtersInfo(HttpServletRequest httpServletRequest)
  {
    String returnValue = null;
    Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
    StringBuilder sb = new StringBuilder();
    sb.append("Request Parameters:");
    sb.append("[");
    String parameterName = null;
    String parameterValue = null;
    while (parameterNames.hasMoreElements())
    {
      parameterName = (String)parameterNames.nextElement();
      parameterValue = httpServletRequest.getParameter(parameterName);
      sb.append(parameterName).append("=").append(parameterValue).append(parameterNames.hasMoreElements() ? "," : "");
    }
    sb.append("]");
    returnValue = sb.toString();
    return returnValue;
  }
  
  public static String getRequestMethodInfo(HttpServletRequest httpServletRequest)
  {
    String returnValue = null;
    StringBuilder sb = new StringBuilder();
    sb.append("Request method:");
    sb.append(httpServletRequest.getMethod());
    returnValue = sb.toString();
    return returnValue;
  }
  public static String getCookieValue(HttpServletRequest httpServletRequest, String cookieName)
  {
    String returnValue = null;
    Cookie[] cookies = httpServletRequest.getCookies();
    if (ArrayUtils.isNotEmpty(cookies)) {
      for (Cookie oneCookie : cookies) {
        if (StringUtils.equalsIgnoreCase(cookieName, oneCookie.getName()))
        {
          returnValue = oneCookie.getValue();
          break;
        }
      }
    }
    return returnValue;
  }
  
  /**
	 * 设置cookie
	 * @param name
	 * @param value
	 * @param domain
	 * @param expire
	 */
	public static void setCookie(HttpServletResponse response,String name, String value, String domain, int expire) {
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
	 * @param name
	 * @param value
	 * @param domain
	 * @param expire
	 */
	public static void setNoDomainCookie(HttpServletResponse response,String name, String value,int expire) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (expire >= 0) {
			cookie.setMaxAge(expire);
		}
		response.addCookie(cookie);
	}

  private static enum ProxyClientIPHttpHeaderNameEnum
  {
    XFORWARDEDFOR("x-forwarded-for"),  PROXYCLIENTIP("Proxy-Client-IP"),  WLPROXYCLIENTIP("WL-Proxy-Client-IP");
    
    private final String headerName;
    
    String getHeaderName()
    {
      return this.headerName;
    }
    
    private ProxyClientIPHttpHeaderNameEnum(String headerName)
    {
      this.headerName = headerName;
    }
    
    public String toString()
    {
      return this.headerName;
    }
  }
}
