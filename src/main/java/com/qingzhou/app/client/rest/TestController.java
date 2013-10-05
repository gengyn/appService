package com.qingzhou.app.client.rest;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service")
public class TestController extends BaseController{
	
	@RequestMapping(value = "/test/{mobile}",method = RequestMethod.GET)
    public @ResponseBody 
    String welcome(@PathVariable String mobile) {  
//        if (true)  
//            throw new RuntimeException("异常异常");  
		new Thread()
		{
			String abc = "";
			
		};
		return "A";
    }    
	
	
   
}
