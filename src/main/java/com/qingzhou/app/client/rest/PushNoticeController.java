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
		String noticeTitle = "";
		String noticeContent = "";
		
		switch(notice.getNotice_type())
		{
			case PushCommon.CLIENT_MYPROJECT:
				noticeTitle = "我的家装";
				noticeContent = "工程进度有更新，查看详情";
				break;
			case PushCommon.CLIENT_MYCONTRACT:
				noticeTitle = "我的合同";
				noticeContent = "我的合同有变更，查看详情";
				break;
			case PushCommon.CLIENT_MYINFO:
				noticeTitle = "资讯";
				noticeContent = "资讯更新了"+notice.getCounts()+"条，查看详情";
				break;
			case PushCommon.CLIENT_MYMESSAGE:
				noticeTitle = "我的消息";
				noticeContent = "新收到了"+notice.getMsg_sendername()+"的"+notice.getCounts()+"条消息，查看详情";
				break;
			default:
				noticeTitle = notice.getNotice_title();
				noticeContent = notice.getNotice_content();
			
		}
		
		Map<String,Object> extra = new HashMap<String,Object>();
		extra.put("NOTICE_TYPE", notice.getNotice_type());
		extra.put("MSG_SENDER", notice.getMsg_sender());
		extra.put("NOTICE_URL", notice.getNotice_url());
		
		MessageResult result = null;
		
		PushUtils.initJpush(notice.getTarget_flag());
		if (!LogicUtils.isEmpty(notice.getAlias()))
			result = PushUtils.sendNotificationWithAlias(notice.getAlias(), noticeTitle, noticeContent, extra);
		else if (!LogicUtils.isEmpty(notice.getTag()))
			result = PushUtils.sendNotificationWithTag(notice.getTag(), noticeTitle, noticeContent, extra);
		else result = PushUtils.sendNotificationWithAppKey(noticeTitle, noticeContent, extra);
		
		return result;

	}
	
}