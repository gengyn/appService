package com.qingzhou.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @commons
 * @author chenyanji
 * @date 2013-4-26 下午04:43:21
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYYMMDD = "yyyy-MM-dd";

	public static java.util.Date getCurrentDate() {
		return new java.util.Date();
	}

	public static java.util.Date getDate(String date) {
		date = date.replaceAll("\\.", "-");
		date = date.replaceAll("/", "-");
		if (date.trim().length() == 10) {
			SimpleDateFormat simpl = new SimpleDateFormat(YYYYMMDD);
			try {
				return simpl.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(date + "日期格式错误");
			}
		} else if (date.trim().length() == 19) {
			SimpleDateFormat simpl = new SimpleDateFormat(YYYYMMDDHHMMSS);
			try {
				return simpl.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(date + "日期格式错误");
			}
		} else {
			throw new RuntimeException(date + "日期格式无法识别");
		}
	}

	public static String getDate(java.util.Date date) {
		SimpleDateFormat simpl = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return simpl.format(date);
	}

	public static void main(String aa[]) {
		System.out.println(getDate(getDate("2013.02.03")));
	}
}
