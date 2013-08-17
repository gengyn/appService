package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.LoginStatusDao;
import com.qingzhou.app.client.domain.UserBase;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;

@Service
public class LoginStatusService  extends BaseService<LoginStatus> {

	@Autowired
	private LoginStatusDao loginStatusDao;	

	public void setLoginStatusDao(LoginStatusDao loginStatusDao) {
		this.loginStatusDao = loginStatusDao;
	}
	

	@Override
	public Integer count(LoginStatus loginStatus) {
		return loginStatusDao.countUser_token(loginStatus);
	}

	public LoginStatus queryByPK(LoginStatus loginStatus)
	{
		return loginStatusDao.selectByPK(loginStatus);
	}
	
	public UserBase create(LoginStatus loginStatus)
	{
		
		try
		{
			//暂时先不增加客户登录状态，只返回客户信息
			//loginStatusDao.insertLoginStatus(loginStatus);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return loginStatusDao.selectByLogin(loginStatus);
	}
	
	public int modify(LoginStatus loginStatus)
	{
		return loginStatusDao.updateLoginStatus(loginStatus);
	}
	
	public int deleteByPKs(LoginStatus loginStatus)
	{
		return loginStatusDao.deleteLoginStatus(loginStatus);
	}

	@Override
	public List<LoginStatus> select(LoginStatus entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
