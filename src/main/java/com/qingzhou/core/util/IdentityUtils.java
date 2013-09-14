package com.qingzhou.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class IdentityUtils {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(IdentityUtils.class);
	
	/**
	 * Generate ID
	 * 
	 * @return ID
	 */
	public static final String generateID() {
		String id = System.currentTimeMillis() + "" + mathRandom(7);
		/*SessionReport report = new SessionReport();
		if(!report.getResponse())
			throw new ProjectException();*/
		return id;
	}
	/**
	 * 产生指定长度的数学随机数
	 * 
	 * @param length 随机数长度
	 * @return 随机数
	 */
	public static final String mathRandom(final long length) {
		double len = Math.pow(10D, length);
		long result = (long) (len * Math.random());

		// 补齐随机数长度
		while (len / result > 10) {
			result *= 10;
		}
		return Long.toString(result);
	}
	
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = MAX/2;
	
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	
	public static void main(String a[]) {
		logger.debug(getRandomSendNo());
	}
}
