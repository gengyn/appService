package com.qingzhou.app.client.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qingzhou.app.client.dao.LoginStatusDao;
import com.qingzhou.app.client.dao.UserLogDao;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.app.client.domain.UserLog;
import com.qingzhou.app.core.controller.RestConstants;
import com.qingzhou.core.exception.AppException;
import com.qingzhou.core.service.BaseService;
@Service
public class UserManageService extends BaseService{

	@Autowired 
	private UserLogDao userLogDao;
	@Autowired 
	private LoginStatusDao loginStatusDao;
	
	public void setUserLogDao(UserLogDao userLogDao) {
		this.userLogDao = userLogDao;
	}
	
	public void setLoginStatusDao(LoginStatusDao loginStatusDao) {
		this.loginStatusDao = loginStatusDao;
	}
	
	/**
	 * 访问服务前处理
	 * @param methodName 调用的方法名称
	 * @param args 获得参数列表
	 * @throws Exception 
	 */
	public void beforeRest(String methodName,Object[] args)
	{
		//我的家装服务、我的合同、轻舟资讯
		if (methodName.equals(RestConstants.METHOD_GET_PROJECTPLAN) 
				|| methodName.equals(RestConstants.METHOD_GET_CONTRACT)
				|| methodName.equals(RestConstants.METHOD_GET_MYINFOLIST))
		{
			String userToken = (String)args[0];
			checkToken(userToken);
			updateUserStatus(userToken);
			
		}
	}
	
	/**
	 * 访问服务后处理
	 * @param methodName 调用的方法名称
	 * @param args 获得参数列表
	 * @param result 成功或失败标识
	 */
	public void afterRest(String methodName,Object[] args,String result)
	{
		//登录服务
		if (methodName.equals(RestConstants.METHOD_POST_LOGIN))
		{
			LoginStatus loginStatus = (LoginStatus) args[0];
			insertOPLog(loginStatus.getUser_token(),methodName,result);
			
		}else
		//我的家装服务，我的合同，轻舟资讯、退出服务、照片列表
		if (methodName.equals(RestConstants.METHOD_GET_PROJECTPLAN)
				||methodName.equals(RestConstants.METHOD_GET_CONTRACT)
				||methodName.equals(RestConstants.METHOD_GET_MYINFOLIST)
				||methodName.equals(RestConstants.METHOD_DEL_LOGIN)
				||methodName.equals(RestConstants.METHOD_GET_SHOWPHOTO)){

			insertOPLog((String)args[0],methodName,result);
		}
	}
	
	/**
	 * 令牌校验
	 * @param user_token
	 */
	private void checkToken(String user_token)
	{
		//1、令牌校验
		if (StringUtils.isEmpty(user_token)) throw new AppException("9998");
		int count = loginStatusDao.countUser_token(user_token);
		if (count == 0) throw new AppException("9998");
	}
	/**
	 * 更新客户登录状态
	 * @param user_token
	 * @throws AppException
	 */
	private void updateUserStatus(String user_token)
	{
		//2、更新客户登录状态
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setUser_token(user_token);
		loginStatusDao.updateLoginStatus(loginStatus);

	}
	
	/**
	 * 写操作日志
	 * @param userLog
	 */
	private void insertOPLog(String token,String methodName,String result)
	{
		try
		{	//日志开关
			if (StringUtils.isEmpty(token) || !RestConstants.ISRECORD_LOGS)
				return;
			LoginStatus loginStatus = loginStatusDao.selectByPK(token);
			//3、写操作日志
			UserLog userLog = new UserLog();
			userLog.setOp_mobile(loginStatus.getUser_phone());
			userLog.setOp_name(loginStatus.getUser_name());
			userLog.setOp_content(methodName);
			userLog.setOp_result(result);
			userLog.setLog_id(UUID.randomUUID().toString());
			userLogDao.insertOPLog(userLog);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public Integer count(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List select(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
