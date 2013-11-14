package com.qingzhou.app.core.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.core.exception.AppException;
import com.qingzhou.core.util.FormUtils;
import com.qingzhou.core.util.ServletUtils;
import com.qingzhou.core.util.SpringContextHolder;

/**
 * Action层父类，包括一些action中常用的方法
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public abstract class BaseController {
	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 异常的统一处理，默认返回9999
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class})  
    public @ResponseBody String setException(Exception ex, HttpServletRequest request) {
		logger.error("服务发生异常", ex);
        return "9999";  
    }
	
	@ExceptionHandler(value = {AppException.class})  
    public @ResponseBody String setAppException(AppException ex, HttpServletRequest request) {
		logger.error("服务发生异常", ex);
        return ex.getCode();  
    }
	
	/**
	 * 根据类来获取相应的spring bean实例,例如：this.getBean(UserService.class)
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public <T> T getBean(Class<T> requiredType) {
		return SpringContextHolder.getBean(requiredType);
	}

	/**
	 * 根据bean名称来获取相应的spring bean实例,例如：this.getBean("userService")
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public <T> T getBean(String name) {
		return SpringContextHolder.getBean(name);
	}

	
	/**
	 * 将页面传入参数按名称绑定至domain对象
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws InstantiationException
	 */
	@SuppressWarnings("static-access")
	protected Object bindFormToObject(Class clazz)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException {
		return FormUtils.getFormUtilsInstance().bindFormToObject(clazz,
				this.getRequest());
	}

	/**
	 * 获得当前的请求request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 根据名称获取request中的参数值
	 * 
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获得当前会话的response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得房前会话
	 * 
	 * @param forceNew
	 *            如果没有得到则强制建立一个会话
	 * @return
	 */
	public HttpSession getSession(boolean forceNew) {
		return getRequest().getSession(forceNew);
	}
	
	/**
	 * 获得房前会话
	 * 
	 * @param forceNew
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 根据名称获取当前会话中的attribute
	 * 
	 * @param name
	 *            名称
	 * @return
	 */
	public Object getSessionAttribute(String name) {
		HttpSession session = getSession();
		return session == null ? null : session.getAttribute(name);
	}

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static String render(final String contentType, final String content,
			final String... headers) {
		HttpServletResponse response = initResponseHeader(contentType, headers);
		try {
			response.getOutputStream()
					.write(content.getBytes(DEFAULT_ENCODING));
			response.getOutputStream().flush();
			// response.getWriter().write(content);
			// response.getWriter().flush();
			return null;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static String renderText(final String text, final String... headers) {
		return render(ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static String renderHtml(final String html, final String... headers) {
		return render(ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static String renderXml(final String xml, final String... headers) {
		return render(ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static String renderJson(final String jsonString,
			final String... headers) {
		return render(ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 暂时保留，直接输出HTML.
	 * <p>
	 * 此处不能用AbstractAction.renderHtml
	 * <p>
	 * 因为：关闭页面后，response.getWriter.write() 在WebLogic下不抛异常 导致无法退出外部循环,
	 * 而这里的out.print()可以抛出异常
	 * 
	 * @param content
	 *            输出内容
	 */
	public void cometRender(final String content) throws IOException {
		HttpServletResponse response = initResponseHeader(ServletUtils.HTML_TYPE);
		ServletOutputStream out = response.getOutputStream();
		out.print(content);
		out.flush();
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(
			final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName
						+ "不是一个合法的header类型");
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}

}
