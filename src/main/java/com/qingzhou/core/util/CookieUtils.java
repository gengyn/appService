package com.qingzhou.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtils {
	private static final int DEFAULT_COOKIE_TIMEOUT = -1; //缺省Cookie过期时间:在浏览器打开期间一直有效


	public static void setCookieValue(HttpServletResponse response, String name, String value) {
		setCookieValue(response, name, value, DEFAULT_COOKIE_TIMEOUT);
	}

	public static void setCookieValue(HttpServletResponse response, String name, String value, int expiredSeconds) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath("/");
		cookies.setMaxAge(expiredSeconds);
		response.addCookie(cookies);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		if (request.getCookies() == null)
			return "";
		for (Cookie cookie : request.getCookies()) {
			if (cookieName.equals(cookie.getName()))
				return cookie.getValue();
		}
		return "";
	}

	public static String getCookieValueFromHeader(HttpServletRequest request, String cookieName) {
		String cookies = request.getHeader("Cookie");
		if (StringUtils.isBlank(cookies))
			return "";
		for (String cookie : cookies.split(";")) {
			String[] cookiePair = StringUtils.split(cookie, "=", 2);
			if (cookieName.equals(StringUtils.trim(cookiePair[0])))
				return StringUtils.trim(cookiePair[1]);
		}
		return "";
	}

}
