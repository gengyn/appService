package com.qingzhou.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @commons
 * @author chenyanji
 * @date 2013-5-6 下午07:07:33
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 
	 * @commons 获取对象xml格式数据
	 * @author chenyanji
	 * @date 2013-5-6 下午07:05:33
	 * @param obj
	 * @return
	 */
	public static String getXML(Object obj) {
		XStream x = new XStream(new DomDriver());
		return x.toXML(obj);
	}

	/**
	 * 将method的大写字母转换为下划线+小写字母 例如: getMsg -> get_msg
	 * 
	 * @param name
	 * @return
	 */
	public static String addUnderscores(String name) {
		if (name == null)
			return null;
		StringBuffer buf = new StringBuffer(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if ('_' != buf.charAt(i - 1) && Character.isUpperCase(buf.charAt(i)) && !Character.isUpperCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}

	public static String getSeter(String str) {
		return "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getGeter(String str) {
		return "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 将method的下划线去掉，并将其后的第一个字母转换为大写字母 例如: dp_gr_query -> DpGrQuery
	 * 
	 * @param name
	 * @return
	 */
	public static String removeUnderscores(String name) {
		if (name == null)
			return null;
		StringBuffer buf = new StringBuffer();
		String[] words = split(name.toLowerCase(), "_");
		for (int i = 0; i < words.length; i++) {
			buf.append(capitalise(words[i]));
		}
		return buf.toString();
	}

	/**
	 * 
	 * @comment dp_gr_query -> dpGrQuery
	 * @author 陈彦吉
	 * @date 2013-5-29 下午1:28:23
	 * @param name
	 * @return
	 */
	public static String getJavaName(String name) {
		if (name == null)
			return null;
		String converted = removeUnderscores(name);
		return new StringBuffer(converted.length()).append(Character.toLowerCase(converted.charAt(0))).append(converted.substring(1))
				.toString();
	}

	public static void main(String a[]) {
		System.out.println(removeUnderscores("aaa_asf_af_sf"));
		System.out.println(addUnderscores(removeUnderscores("aaa_asf_af_sf")));
	}
}
