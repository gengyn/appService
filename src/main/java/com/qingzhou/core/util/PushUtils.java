package com.qingzhou.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

import com.qingzhou.app.jpush.PushCommon;
/**
 * 消息推送工具类
 * @author hihi
 *
 */
public class PushUtils {
	private static final Log logger = LogFactory.getLog(PushUtils.class);
	
	public static int resultCode = 0;
	public static JPushClient jpush = null;

	
	/**
	 * 初始化JPushClient
	 */
	public static void initJpush(int flag)
	{
		if (flag == PushCommon.CLIENT_FLAG)
			jpush = new JPushClient(PushCommon.CLIENT_API_MasterSecret, 
					PushCommon.CLIENT_AppKey,
					PushCommon.CLIENT_timeToLive);
		else if (flag == PushCommon.EMPLOYEE_FLAG)
			jpush = new JPushClient(PushCommon.EMPLOYEE_API_MasterSecret, 
					PushCommon.EMPLOYEE_AppKey,
					PushCommon.EMPLOYEE_timeToLive);
	}
	
	/**
	 * 发送带AppKey的自定义通知
	 */
	public static MessageResult sendNotificationWithAppKey(String msgTitle,String msgContent,Map<String,Object> extra)
	{
		MessageResult result = jpush.sendNotificationWithAppKey(
				IdentityUtils.getRandomSendNo(), 
				msgTitle, 
				msgContent,
				0,
				extra);
		handleResult(result);
		return result;
	}
	
	
	/**
	 * 发送带ALIAS的通知
	 * @param Alias
	 * @param msgTitle
	 * @param msgContent
	 */
	public static MessageResult sendNotificationWithAlias(String alias,String msgTitle,String msgContent,Map<String,Object> extra)
	{
		MessageResult result = null;
		if (extra!=null)
			result = jpush.sendNotificationWithAlias(
					IdentityUtils.getRandomSendNo(), alias, msgTitle, msgContent, 0, extra);
		else 
			result = jpush.sendNotificationWithAlias(
				IdentityUtils.getRandomSendNo(), alias, msgTitle, msgContent);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送带tag的通知
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 */
	public static MessageResult sendNotificationWithTag(String tag,String msgTitle,String msgContent,Map<String,Object> extra)
	{
		MessageResult result = null;
		if (extra!=null)
			result = jpush.sendNotificationWithTag(
					IdentityUtils.getRandomSendNo(), tag, msgTitle, msgContent, 0, extra);
		else 
			result = jpush.sendNotificationWithTag(
					IdentityUtils.getRandomSendNo(), tag, msgTitle, msgContent);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送带AppKey的自定义消息
	 * @param msgTitle
	 * @param msgContent
	 */
	public static MessageResult sendCustomMessageWithAppKey(String msgTitle,String msgContent)
	{
		MessageResult result = jpush.sendCustomMessageWithAppKey(
				IdentityUtils.getRandomSendNo(), msgTitle, msgContent);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送带Alias的自定义消息
	 * @param alias
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static MessageResult sendCustomMessageWithAlias(String alias,String msgTitle,String msgContent)
	{
		MessageResult result = jpush.sendCustomMessageWithAlias(
				IdentityUtils.getRandomSendNo(), alias, msgTitle, msgContent);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送带TAG的自定义消息
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static MessageResult sendCustomMessageWithTag(String tag,String msgTitle,String msgContent)
	{
		MessageResult result = jpush.sendCustomMessageWithTag(
				IdentityUtils.getRandomSendNo(), tag, msgTitle, msgContent);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送即时消息
	 * @param alias
	 * @param msgContent
	 * @param extra
	 * @return
	 */
	public static MessageResult sendMsgWithAlias(String alias,String msgContent,Map<String,Object> extra)
	{
		MessageResult result = jpush.sendCustomMessageWithAlias(
				IdentityUtils.getRandomSendNo(), alias, null, msgContent, null, extra);
		handleResult(result);
		return result;
	}
	
	/**
	 * 发送即时消息，ID外部生成
	 * @param id
	 * @param alias
	 * @param msgContent
	 * @param extra
	 * @return
	 */
	public static MessageResult sendMsgWithAlias(int id,String alias,String msgContent,Map<String,Object> extra)
	{
		MessageResult result = jpush.sendCustomMessageWithAlias(
				id, alias, null, msgContent, null, extra);
		handleResult(result);
		return result;
	}

	/**
	 * 消息返回处理
	 * @param result
	 */
	public static void handleResult(MessageResult result)
	{
		if (null != result) {
		    if (result.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
		    	logger.debug("发送成功， sendNo=" + result.getSendno());
		    } else {
		    	logger.error("发送失败， 错误代码=" + result.getErrcode() + ", 错误消息=" + result.getErrmsg());
		    }
		} else {
			logger.error("无法获取数据");
		}

	}
 
	
	public static void main(String a[]) {
		initJpush(PushCommon.CLIENT_FLAG);
		String msgTitle = "消息标题";
		String msgContent = "消息内容";
//		sendNotificationWithAppKey(msgTitle,msgContent);
//		sendNotificationWithAlias("hihi","alias标题","alias内容");
//		sendNotificationWithTag("gyn","tag标题","tag内容");
		//sendCustomMessageWithAppKey(msgTitle,msgContent);
		//sendCustomMessageWithAlias("hihi","alias标题","alias内容");
		//sendCustomMessageWithTag("gyn","tag标题","tag内容");
		Map<String,Object> tempMap = new HashMap<String,Object>();
		tempMap.put("msg_type", "PROJECTPLAN");
		sendNotificationWithAlias("hihi","我的家装","您的工程进度有更新",tempMap);
//		sendNotificationWithTag("gyn","tag标题","tag内容");
	}
	
}
