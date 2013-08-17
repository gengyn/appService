package com.qingzhou.app.client.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.UserBase;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.app.client.service.LoginStatusService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/login")
public class LoginStatusController extends BaseController{

	private LoginStatusService loginStatusService;

	/**
	 * 新增客户登录信息,并返回获取合同信息
	 * @param loginStatus
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody 
	UserBase postLoginJSON(@RequestBody LoginStatus loginStatus ) {
		
		logger.info("新增客户登录信息"+loginStatus.getUser_name()+loginStatus.getUser_token()+loginStatus.getUser_phone());
		loginStatusService = this.getBean("loginStatusService");
		return loginStatusService.create(loginStatus);

	}
	
	/**
	 * 更新最新操作时间
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	int putLoginJSON(@RequestBody LoginStatus loginStatus) {
		
		logger.info("更新最新操作时间" + loginStatus.getUser_token());
		loginStatusService = this.getBean("loginStatusService");
		return loginStatusService.modify(loginStatus);

	}
	/**
	 * 删除客户登录信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value= "/{user_token}",method = RequestMethod.DELETE)
	public @ResponseBody
	int delLoginJSON(@PathVariable String user_token) {
		
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setUser_token(user_token);
		logger.info("删除客户登录信息" + loginStatus.getUser_token() );
		loginStatusService = this.getBean("loginStatusService");
		return loginStatusService.deleteByPKs(loginStatus);

	}

}