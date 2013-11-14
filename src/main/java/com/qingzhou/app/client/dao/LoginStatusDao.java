package com.qingzhou.app.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.UserBase;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface LoginStatusDao {
	
	public int countUser_token(String user_token);
	
	public LoginStatus selectByPK(String user_token); 
	
	public Integer insertLoginStatus(LoginStatus loginStatus);

	public Integer updateLoginStatus(LoginStatus loginStatus);
	
	public Integer deleteLoginStatus(String user_token);
	
	public Integer deleteLoginStatusByTime(double dateCondition);
	
	/**
	 * 登录时获取客户相关信息
	 * @param contract
	 * @return
	 */
	public UserBase selectByLogin(LoginStatus loginStatus);

}
