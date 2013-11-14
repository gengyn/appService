package com.qingzhou.core.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.qingzhou.core.domain.LoginUser;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.exception.AppException;
import com.qingzhou.core.util.CookieUtils;
import com.qingzhou.core.util.FormUtils;
import com.qingzhou.core.util.ServletUtils;
import com.qingzhou.core.util.SpringContextHolder;

/**
 * Action层父类，包括一些action中常用的方法
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public abstract class BaseAction extends ActionSupport implements Preparable {
	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	/**
	 * Cookie中存在的用户信息
	 */
	public static final String LOGIN_USER_ID = "userId";
	public static final String LOGIN_SEAT_SOFT_ID = "seatSoftId";
	public static final String LOGIN_SEAT_ID = "seatId";
	public static final String LOGIN_ROLE_ID = "roleId";
	public static final String LOGIN_USER_NAME = "userName";

	/**
	 * 系统首页参数 例如首页地址为http://localhost:8089/webdemo/test/aircraft!index.action
	 * 参数为 loginUrl=/aircraft!index.action
	 */
	public static final String LOGIN_URL = "loginUrl";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, Object> valuesMap = new HashMap<String, Object>();

	public Map<String, Object> getValuesMap() {
		return valuesMap;
	}

	public void setValuesMap(Map<String, Object> valuesMap) {
		this.valuesMap = valuesMap;
	}

	public void prepare() throws Exception {
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
	 * 从rquest中将页面DataTable分页组件传过的参数绑定至需要分页的对象中
	 * 
	 * @param clazz
	 *            集成Page的domain对象
	 * @return clazz
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("static-access")
	protected Object bindDataTableParm(Class<? extends Page> clazz)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException {
		return FormUtils.getFormUtilsInstance().bindDataTableParm(clazz,
				this.getRequest());
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

	/**
	 * 自动跳转main函数，能够按照url自动跳转并且能够将用户信息保存到cookie中去
	 * 例如首页地址为http://localhost:8089/webdemo/test/aircraft!index.action
	 * 访问时可访问http
	 * ://localhost:8089/webdemo/test/aircraft!main.action?loginUrl=/aircraft
	 * !index.action
	 * 
	 * @return
	 */
	public String main() {
		try {
			LoginUser loginUser = FormUtils.getFormUtilsInstance()
					.bindFormToObject(LoginUser.class, this.getRequest());
			this.setLoginUser(loginUser);
			this.getResponse().sendRedirect(
					this.getRequest().getParameter(LOGIN_URL));
		} catch (Exception e) {
			AppException appException = AppException.getException("0002", e);
			logger.error(appException.getCode() + appException.getMessage(),
					appException);
			throw AppException.getException("0002", e);
		}
		return null;
	}

	/**
	 * 获取请求中的参数Map
	 * 
	 * @return
	 */
	public Map<String, Object> getParemetersMap() {
		return this.getRequest().getParameterMap();
	}

	/**
	 * 将登陆的用户信息保存到cookie中去
	 * 
	 * @param loginUser
	 * @return
	 */
	public LoginUser setLoginUser(LoginUser loginUser) {
		CookieUtils.setCookieValue(this.getResponse(), LOGIN_USER_ID,
				loginUser.getUserId());
		CookieUtils.setCookieValue(this.getResponse(), LOGIN_ROLE_ID,
				loginUser.getRoleId());
		CookieUtils.setCookieValue(this.getResponse(), LOGIN_SEAT_ID,
				loginUser.getSeatId());
		CookieUtils.setCookieValue(this.getResponse(), LOGIN_SEAT_SOFT_ID,
				loginUser.getSeatSoftId());
		CookieUtils.setCookieValue(this.getResponse(), LOGIN_USER_NAME,
				loginUser.getUserName());
		return loginUser;
	}

	/**
	 * 从cookie中读取登陆用户信息
	 * 
	 * @return
	 */
	public LoginUser getLoginUser() {
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(CookieUtils.getCookieValue(this.getRequest(),
				LOGIN_USER_ID));
		loginUser.setRoleId(CookieUtils.getCookieValue(this.getRequest(),
				LOGIN_ROLE_ID));
		loginUser.setSeatId(CookieUtils.getCookieValue(this.getRequest(),
				LOGIN_SEAT_ID));
		loginUser.setSeatSoftId(CookieUtils.getCookieValue(this.getRequest(),
				LOGIN_SEAT_SOFT_ID));
		loginUser.setUserName(CookieUtils.getCookieValue(this.getRequest(),
				LOGIN_USER_NAME));
		if(StringUtils.isEmpty(loginUser.getUserId()))
			loginUser=new LoginUser("000","000","000","000","测试");
		//TODO 临时写死默认值，待与席位集成框架集成后修改
		return  loginUser ;
	}
}
