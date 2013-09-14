package com.qingzhou.app.jpush;

/**
 * 消息推送的公共参数
 * @author hihi
 *
 */
public class PushCommon {
	
	//客户版
	public final static int CLIENT_FLAG = 0;
//	public final static String CLIENT_AppKey = "510d191e84659ead7413fce8";      
//	public final static String CLIENT_API_MasterSecret = "2662f7ff7102b816cc5ddc19";
	public final static String CLIENT_AppKey = "7fb50bbcb9d18e80b9e09d41";      
	public final static String CLIENT_API_MasterSecret = "f191b2631801fdaf876f0d19";
	public final static int CLIENT_timeToLive =  60 * 60 * 24;       //保存离线的时长，默认为1天
	
	public final static int CLIENT_OTHER = 0x00;//非栏目性的通知
	public final static int CLIENT_MYPROJECT = 0x01; //我的家装
	public final static int CLIENT_MYCONTRACT = 0x02;//我的合同
	public final static int CLIENT_MYINFO = 0x03;//轻舟资讯
	public final static int CLIENT_MYMESSAGE = 0x04;//我的消息
	
	
	//员工版
	public final static int EMPLOYEE_FLAG = 1;
	public final static String EMPLOYEE_AppKey = "";      
	public final static String EMPLOYEE_API_MasterSecret = "";
	public final static int EMPLOYEE_timeToLive =  60 * 60 * 24;
	
	
}
