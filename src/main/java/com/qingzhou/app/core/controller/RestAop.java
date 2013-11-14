package com.qingzhou.app.core.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qingzhou.app.client.service.UserManageService;
import com.qingzhou.core.exception.AppException;
import com.qingzhou.core.util.SpringContextHolder;

/**
 * Rest服务的AOP，管理服务性能，用户登录状态等
 * @author hihi
 *
 */
@Component
@Aspect
public class RestAop{
	protected Logger logger = LoggerFactory.getLogger(RestAop.class);
	
	public RestAop()
	{
		logger.info("启用AOP........");
	}
	@Pointcut(value =("execution(* com.qingzhou.app.client.rest..*.*(..))"))
	private void pointCut() {  
		  
    } 
	
	@Before(value = "pointCut()")
	public void doBefore(JoinPoint jp)  {
		logger.debug("Aop:before..............");
		UserManageService userManageService = SpringContextHolder.getBean("userManageService");
		userManageService.beforeRest(jp.getSignature().getName(), jp.getArgs());
    }
	
	@After(value = "pointCut()")
	public void doAfter()
	{
		logger.debug("Aop:after..............");
	}
	
	@AfterReturning(value = "pointCut()", returning = "obj")  
    public void doAfterReturning(JoinPoint jp,Object obj) {  
		logger.debug("Aop:doAfterReturning..............");
		//logger.debug("返回的值是:" + obj);  
		UserManageService userManageService = SpringContextHolder.getBean("userManageService");
		userManageService.afterRest(jp.getSignature().getName(), jp.getArgs(),"0");
    }  
	
	@AfterThrowing(value = "pointCut()",throwing = "ex")
	public void doAfterThrowing(JoinPoint jp,Throwable ex)
	{
		logger.debug("Aop:AfterThrowing..............");
		logger.error("发生异常：{}",ex.getStackTrace());
		UserManageService userManageService = SpringContextHolder.getBean("userManageService");
		userManageService.afterRest(jp.getSignature().getName(), jp.getArgs(),ex.getMessage());
	}
	
	@Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint pjp)  throws Throwable   
    {  
		logger.debug("Aop:Around..................");

        logger.debug("开始调用服务"+pjp.getThis().toString()+pjp.getSignature().getName());
    	long beginTime = System.currentTimeMillis();//1、开始时间  

        Object retVal = pjp.proceed(); 
        	
        long endTime = System.currentTimeMillis();//2、结束时间  
        long consumeTime = endTime - beginTime;//3、消耗的时间  
        logger.info(String.format("%s consume %d millis", "服务"+pjp.getThis().toString()+pjp.getSignature().getName(), consumeTime));
            
        return retVal;  
    } 

}
