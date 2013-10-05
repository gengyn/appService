package com.qingzhou.app.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.LoginStatusDao;
import com.qingzhou.app.client.domain.UserBase;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.core.service.BaseService;

@Service
public class LoginStatusService  extends BaseService<LoginStatus> {

	@Autowired
	private LoginStatusDao loginStatusDao;	

	public void setLoginStatusDao(LoginStatusDao loginStatusDao) {
		this.loginStatusDao = loginStatusDao;
	}
	

	public LoginStatus queryByPK(String user_token)
	{
		return loginStatusDao.selectByPK(user_token);
	}
	
	public UserBase create(LoginStatus loginStatus)
	{
		
		//新增客户登录状态
		loginStatusDao.insertLoginStatus(loginStatus);
		return loginStatusDao.selectByLogin(loginStatus);
	}
	
	public int modify(LoginStatus loginStatus)
	{
		return loginStatusDao.updateLoginStatus(loginStatus);
	}
	
	public int deleteByPKs(LoginStatus loginStatus)
	{
		return loginStatusDao.deleteLoginStatus(loginStatus.getUser_token());
	}
	
	public int deleteLoginStatusByTime(double dateCondition)
	{
		return loginStatusDao.deleteLoginStatusByTime(dateCondition);
	}

	@Override
	public List<LoginStatus> select(LoginStatus entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer count(LoginStatus entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
