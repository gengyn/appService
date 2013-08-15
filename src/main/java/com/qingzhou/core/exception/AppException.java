package com.qingzhou.core.exception;

import java.text.MessageFormat;

import com.qingzhou.core.cache.AppCache;


/**
 * 
 * 系统异常类
 * 
 */
@SuppressWarnings("serial")
public class AppException extends RuntimeException {
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

	/**
	 * 异常封装工具
	 * 
	 * @param code
	 *            错误码
	 * @param throwable
	 *            异常
	 * @return
	 */
	public static AppException getException(String code, Throwable throwable) {
		return new AppException(code, AppCache.errorInfoConfig.get(code),
				throwable);
	}

	/**
	 * 异常封装工具
	 * 
	 * @param code
	 *            错误码
	 * @param throwable
	 *            异常
	 * @param args
	 *            信息参数
	 * @return
	 */
	public static AppException getException(String code, Throwable throwable,
			Object... args) {
		return new AppException(code, AppCache.errorInfoConfig.get(code),
				throwable).replaceErrorMessage(args);
	}

	/**
	 * 根据定义的异常格式将参数传如形成相应的异常提示
	 * 
	 * @param args
	 * @return
	 */
	private AppException replaceErrorMessage(Object... args) {
		if(null!=this.message&&!"".equalsIgnoreCase(this.message)&&args!=null&&args.length!=0)
			this.message=MessageFormat.format(this.message, args);
		return this;
	}

	private AppException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
		this.message = message;
	}

	private AppException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	private AppException(String message) {
		super(message);
		this.message = message;
	}

	private AppException(Exception ex) {
		super(ex);
	}
}
