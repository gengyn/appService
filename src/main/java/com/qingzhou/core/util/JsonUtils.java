package com.qingzhou.core.util;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}
}
