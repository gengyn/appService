package com.qingzhou.app.client.rest;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.Myinfo;
import com.qingzhou.app.client.service.MyinfoService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/info")
public class MyinfoController extends BaseController{

	private MyinfoService myinfoService;
	
	
	@RequestMapping(value = "/{user_token}/{customer_id}/{pageno}/{pagesize}", method = RequestMethod.GET)
	public @ResponseBody
	List<Myinfo> getMyInfoListJSON(
			@PathVariable String user_token,
			@PathVariable String customer_id,
			@PathVariable String pageno,
			@PathVariable String pagesize) {
		
		logger.info("根据客户ID获取资讯信息，customer_id=" + customer_id);
		
		myinfoService = this.getBean("myinfoService");
		return myinfoService.listInfo(customer_id,pageno,pagesize);
	}
	
}