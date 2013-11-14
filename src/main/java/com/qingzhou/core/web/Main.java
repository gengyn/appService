package com.qingzhou.core.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qingzhou.core.exception.AppException;

public class Main {

	public static void main(String[] args)
	{
		//PropertyUtils.loadProperty("mq.properties");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-core.xml");
			
			System.out.println(PromptMessage.createSuccessPrompt("0003", "用戶")
					.toJSonString());
			System.out.println(PromptMessage.createErrorPrompt("1003",
					AppException.getException("1001",  new Exception("操作错误"), "用戶"))
					.toJSonString());
			System.out.println(PromptMessage.createInfoPrompt("1001", "用戶")
					.toJSonString());
			System.out.println(PromptMessage.createErrorPrompt("1001", "用戶")
					.toJSonString());
			System.out.println(PromptMessage.createWarnPrompt("1001", "用戶")
					.toJSonString());
	}
}
