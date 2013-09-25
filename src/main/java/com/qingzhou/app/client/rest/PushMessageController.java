package com.qingzhou.app.client.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.MessageResult;

import com.qingzhou.app.client.domain.MyMessage;
import com.qingzhou.app.client.service.MyMessageService;
import com.qingzhou.app.core.controller.BaseController;
import com.qingzhou.core.util.LogicUtils;
import com.qingzhou.core.util.PushUtils;
import com.qingzhou.core.util.IdentityUtils;

@Controller
@RequestMapping("/service/push/message")
public class PushMessageController extends BaseController{

	private MyMessageService messageService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody 
	String sendMessage(@RequestBody MyMessage message) {
		
		logger.info("发送消息接口接口："+message.getMessage_content());
		logger.info("发送给"+message.getTarget_flag());
		
		Map<String,Object> extra = new HashMap<String,Object>();
		extra.put("SENDER", message.getSender());
		extra.put("RECEIVER", message.getReceiver());
		extra.put("IMG_URL", message.getImg_url());
		extra.put("VOICE_URL", message.getVoice_url());
		
		MessageResult result = null;
		message.setMessage_id(IdentityUtils.getRandomSendNo());
		message.setMessage_type("00");//默认为即时消息
		
		PushUtils.initJpush(message.getTarget_flag());
		if (!LogicUtils.isEmpty(message.getReceiver()))
			result = PushUtils.sendMsgWithAlias(
					message.getMessage_id(),
					message.getReceiver(), 
					message.getMessage_content(), extra);
		else logger.error("缺少接收者，不能发送消息");
		
		//如果发送失败，设置标志，并扔到重发队列
		if (result.getErrcode() != 0)
			message.setIssucceed("1");
		messageService = this.getBean("myMessageService");
		messageService.insertMessage(message);

		return result.getErrcode()+"";

	}
	
	@RequestMapping(value = "/{mobile}", method = RequestMethod.GET)
	public @ResponseBody
	String getMyMessageJSON(@PathVariable String mobile) {
		
		logger.info("根据客户手机号触发消息重发，这时客户为消息接收者，mobile=" + mobile);
		
		messageService = this.getBean("myMessageService");
		MyMessage message = new MyMessage();
		message.setReceiver(mobile);
		message.setIssucceed("1");//未成功的
		
		int reCount = 0;//重发成功条数
		List<MyMessage> messageList = messageService.listMessage(message);
		for(MyMessage msgItem : messageList)
		{
			Map<String,Object> extra = new HashMap<String,Object>();
			extra.put("SENDER", msgItem.getSender());
			extra.put("RECEIVER", msgItem.getReceiver());
			extra.put("IMG_URL", msgItem.getImg_url());
			extra.put("VOICE_URL", msgItem.getVoice_url());
			
			PushUtils.initJpush(msgItem.getTarget_flag());
			MessageResult result = PushUtils.sendMsgWithAlias(
					msgItem.getMessage_id(),
					msgItem.getReceiver(), 
					msgItem.getMessage_content(), extra);
			
			if (result.getErrcode() == 0)
			{
				reCount ++;
				msgItem.setIssucceed("0");
				messageService.updateMessage(msgItem);
			}
		}
		
		return reCount+"";
	}
	
	
}