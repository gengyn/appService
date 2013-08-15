package com.qingzhou.core.util;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;

public class TimeUtils {

	public static Date now() {
		return new Date();
	}

	public static String formatNow(String format) {
		return DateTimeFormat.forPattern(format).print(System.currentTimeMillis());
	}

	public static String formatNow() {
		return formatNow("yyyyMMddHHmmssSSS");
	}

}
