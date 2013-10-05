package com.qingzhou.app.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RestInterceptor extends HandlerInterceptorAdapter {  
	
	protected Logger logger = LoggerFactory.getLogger(RestInterceptor.class);
	private NamedThreadLocal<Long>  startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
    /** 
     * This implementation always returns <code>true</code>. 
     */ 
	 @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
        throws Exception {  
    	logger.error("preHandle");
    	long beginTime = System.currentTimeMillis();//1、开始时间  
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;  
    }  
  
    /** 
     * This implementation is empty. 
     */  
	 @Override
    public void postHandle(  
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  
            throws Exception {  
    	logger.error("postHandle");
    }  
  
    /** 
     * This implementation is empty. 
     */  
	 @Override
    public void afterCompletion(  
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception { 
    	logger.error("afterCompletion");
    	long endTime = System.currentTimeMillis();//2、结束时间  
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long consumeTime = endTime - beginTime;//3、消耗的时间  
        logger.error(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
    }  
}  
