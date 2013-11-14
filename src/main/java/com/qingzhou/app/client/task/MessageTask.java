package com.qingzhou.app.client.task;

import com.qingzhou.app.client.service.MyMessageService;
import com.qingzhou.app.core.controller.BaseController;

/**
 * 消息定期清理任务，清理间隔配置在application.properties中
 * @author hihi
 *
 */
public class MessageTask extends BaseController{
	
	private MyMessageService messageService;
	
	private int pastdate;//过期天数
	
	public void doClearPastMessage()
	{
		logger.debug("执行清除消息任务");
		try
		{
			messageService = this.getBean("myMessageService");
			messageService.deleteMessageByDate(pastdate);
			
		}catch(Exception ex)
		{
			logger.error("执行清除消息任务失败"+ex.toString());
		}
		
	}
	
	public int getPastdate() {
		return pastdate;
	}

	public void setPastdate(int pastdate) {
		this.pastdate = pastdate;
	}

}
