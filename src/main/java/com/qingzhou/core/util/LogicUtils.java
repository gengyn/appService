package com.qingzhou.core.util;

public class LogicUtils {
	
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length()==0);
	}
	public static boolean isEmpty(String str, String emptyStr) {
		return (isEmpty(str) || str.trim().equalsIgnoreCase(emptyStr));
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str, String emptyStr) {
		return !isEmpty(str, emptyStr);
	}
	
	public static boolean isEquals(String one, String two) {
		if (one != null && two != null) {
			return one.trim().equals(two.trim());
		} else if (one == null && two == null) {
			return true;
		} else if (one == null && two.trim().length() == 0) {
			return true;
		} else if (two == null && one.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEqualsIgnoreCase(String one, String two) {
		if (one != null && two != null) {
			return one.trim().equalsIgnoreCase(two.trim());
		} else if (one == null && two == null) {
			return true;
		} else if (one == null && two.trim().length() == 0) {
			return true;
		} else if (two == null && one.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
