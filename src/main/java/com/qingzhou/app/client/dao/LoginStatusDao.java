package com.qingzhou.app.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface LoginStatusDao {
	
	public int countUser_token(LoginStatus loginStatus);
	
	public LoginStatus selectByPK(LoginStatus loginStatus); 
	
	public Integer insertLoginStatus(LoginStatus loginStatus);

	public Integer updateLoginStatus(LoginStatus loginStatus);
	
	public Integer deleteLoginStatus(LoginStatus loginStatus);

}
