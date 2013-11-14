package com.qingzhou.app.client.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import cn.jpush.api.MessageResult;

import com.qingzhou.app.client.domain.Notice;
import com.qingzhou.app.core.controller.BaseController;
import com.qingzhou.app.jpush.PushCommon;
import com.qingzhou.core.util.LogicUtils;
import com.qingzhou.core.util.PushUtils;

@Controller
@RequestMapping("/service/push/notice")
public class PushNoticeController extends BaseController{

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody 
	MessageResult sendNotice(@RequestBody Notice notice) {
		
		logger.info("发送通知接口："+notice.getNotice_type());
		if (LogicUtils.isEmpty(notice.getNotice_title()) || LogicUtils.isEmpty(notice.getNotice_content()))
		{
		
			switch(notice.getNotice_type())
			{
				case PushCommon.CLIENT_MYPROJECT:
					notice.setNotice_title("我的家装");
					notice.setNotice_content("工程进度有更新，查看详情");
					break;
				case PushCommon.CLIENT_MYCONTRACT:
					notice.setNotice_title("我的合同");
					notice.setNotice_content("我的合同有变更，查看详情");
					break;
				case PushCommon.CLIENT_MYINFO:
					notice.setNotice_title("资讯");
					notice.setNotice_content("资讯更新了"+notice.getCounts()+"条，查看详情");
					break;
				case PushCommon.CLIENT_MYMESSAGE:
					
					notice.setNotice_title("我的消息");
					notice.setNotice_content("新收到了"+notice.getMsg_sendername()+"发送的"+notice.getCounts()+"条消息，查看详情");
					break;
			}
		}
		
		Map<String,Object> extra = new HashMap<String,Object>();
		extra.put("NOTICE_TYPE", notice.getNotice_type());
		extra.put("MSG_SENDER", notice.getMsg_sender());
		extra.put("NOTICE_URL", notice.getNotice_url());
		
		MessageResult result = null;
		
		PushUtils.initJpush(notice.getTarget_flag());
		if (!LogicUtils.isEmpty(notice.getAlias()))
			result = PushUtils.sendNotificationWithAlias(notice.getAlias(), notice.getNotice_title(), notice.getNotice_content(), extra);
		else if (!LogicUtils.isEmpty(notice.getTag()))
			result = PushUtils.sendNotificationWithTag(notice.getTag(), notice.getNotice_title(), notice.getNotice_content(), extra);
		else result = PushUtils.sendNotificationWithAppKey(notice.getNotice_title(), notice.getNotice_content(), extra);
		
		return result;

	}
	
}