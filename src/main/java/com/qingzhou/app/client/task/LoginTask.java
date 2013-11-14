package com.qingzhou.app.client.task;

import com.qingzhou.app.client.service.LoginStatusService;
import com.qingzhou.app.core.controller.BaseController;

/**
 * 客户状态管理定时任务
 * @author hihi
 *
 */
public class LoginTask extends BaseController{
	
	private double onlinelimit;
	private LoginStatusService loginService;

	public void doClearUserOnline()
	{
		logger.debug("开始清除客户登录状态");
		try
		{
			double dateCondition = onlinelimit/1440;
			loginService = this.getBean("loginStatusService");
			loginService.deleteLoginStatusByTime(dateCondition);
		}catch(Exception ex)
		{
			logger.error("执行清除客户登录状态任务失败"+ex.toString());
		}
	}
	
	public double getOnlinelimit() {
		return onlinelimit;
	}

	public void setOnlinelimit(double onlinelimit) {
		this.onlinelimit = onlinelimit;
	}

}
