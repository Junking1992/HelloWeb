package com.web.common;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * 日期工具类
 * @author xiulong.zhang
 * 
 */
public class DateUtils {

	public static final String default_pattern = "yyyy-MM-dd HH:mm:ss";
	
	// 根据日期根据将date类型的日期转化为string
	public static String formatDate(Date date, String pattern) {
		if (null == date){
			return null;
		}
		if (StringUtils.isBlank(pattern)){
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	// 根据yyyy-MM-dd HH:mm:ss 将date类型转化为string
	public static String defaultFormatDate(Date date) {
		if (null == date){
			return null;
		}
		return formatDate(date, default_pattern);
	}
	
	/**
	 * 日期格式为yyyy-MM-dd HH:mm:ss
	 * @param dates
	 * @return
	 */
	public static Date defaultParseDate(String dates){
		if(StringUtils.isBlank(dates)){ return null;}
		return parseDate(dates, default_pattern);
	}
	
	/**
	 * 指定日期格式
	 * @param dates
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dates,String pattern){
		if(StringUtils.isBlank(dates)){ return null;}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dates);
		} catch (ParseException e) {
		}
		return null;
	}
}
