package com.qingzhou.core.util;

import org.apache.commons.lang.StringUtils;

public class FixStringUtils {

	public static StringBuffer append(StringBuffer buffer, String text) {
		buffer.append(text);
		return buffer;
	}

	public static StringBuffer appendDefault(StringBuffer buffer, String text, String defaultText) {
		buffer.append(StringUtils.defaultIfEmpty(text, defaultText));
		return buffer;
	}

	public static StringBuffer appendLpad(StringBuffer buffer, String text, int length, char padChar) {
		buffer.append(StringUtils.leftPad(StringUtils.defaultIfEmpty(text, ""), length, padChar));
		return buffer;
	}

	public static StringBuffer appendRpad(StringBuffer buffer, String text, int length, char padChar) {
		buffer.append(StringUtils.rightPad(StringUtils.defaultIfEmpty(text, ""), length, padChar));
		return buffer;
	}


	public static StringBuffer appendRpad(StringBuffer buffer, String text, String encoding, int length, char padChar) {
		int diffLength = text.getBytes().length - text.length();
		buffer.append(StringUtils.rightPad(StringUtils.defaultIfEmpty(text, ""), length - diffLength, padChar));
		return buffer;
	}

}
