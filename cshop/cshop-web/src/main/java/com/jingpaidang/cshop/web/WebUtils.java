/*
 *
 *  * Copyright 2013-2014 the original author or authors.
 *  *
 *  * http://www.jingpaidang.com
 *
 */

package com.jingpaidang.cshop.web;

import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
    /**
	 * 获取Ip地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}

	public static void printStackTrace(Throwable t, PrintWriter out) {
		if(t == null || out == null) return;
		t.printStackTrace(out);
	}

	public static String getRootUrl(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		String url = sb.toString();
		if (!url.endsWith("/")) url = url + "/";
		return url;
	}

	public static String getCookie(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) throw new NullPointerException();
		Cookie[] cookies = request.getCookies();
		if (cookies == null) return null;
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (StringUtils.isBlank(cookieName)) continue;
			if (name.equals(cookieName)) return cookie.getValue();
		}
		return null;
	}

	public static String getMiliaoFromCookie(HttpServletRequest request) {
		return getCookie(request, "userId");
	}

	public static void deleteCookie(HttpServletResponse response, String name, String path, String domain) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		cookie.setPath(path);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}

	public static void addCookie(HttpServletResponse response,String name,String value,String path,String domain){
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}
}
