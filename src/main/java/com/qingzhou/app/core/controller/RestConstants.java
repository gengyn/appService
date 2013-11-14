package com.qingzhou.app.core.controller;

/**
 * rest服务常量
 * @author HIHI
 *
 */
public class RestConstants {

	public final static boolean ISRECORD_LOGS = false;//是否记录日志开关
	public final static String METHOD_GET_PROJECTPLAN = "getProjectPhanByIDJSON";//获取我的家装列表
	public final static String METHOD_GET_SHOWPHOTO = "getProjectPhotoByIDJSON";//获取照片列表
	public final static String METHOD_GET_MYINFOLIST = "getMyInfoListJSON";//获取轻舟资讯列表
	public final static String METHOD_GET_CONTRACT = "getContractByIDJSON";//获取我的合同
	public final static String METHOD_POST_LOGIN = "postLoginJSON";//新增客户登录信息,并返回获取客户基本信息
	public final static String METHOD_DEL_LOGIN = "delLoginJSON";//退出登录删除客户登录信息
	public final static String METHOD_POST_SENDMESSAGE = "sendMessage";//发送即时消息
	public final static String METHOD_GET_REMESSAGE = "getReMessageJSON";//消息重发

}
