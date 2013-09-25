package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.MyMessageDao;
import com.qingzhou.app.client.domain.MyMessage;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;



@Service
public class MyMessageService  extends BaseService<MyMessage> {

	@Autowired
	private MyMessageDao myMessageDao;	

	public void setMyMessageDao(MyMessageDao myMessageDao) {
		this.myMessageDao = myMessageDao;
	}
	public List<MyMessage> listMessage(MyMessage message) {
		return myMessageDao.listMessage(message);
	}
		
	public int insertMessage(MyMessage message)
	{
		return myMessageDao.insertMessage(message);
	}
	
	public int updateMessage(MyMessage message)
	{
		return myMessageDao.updateMessage(message);
	}
	
	public int deleteMessageByDate(String dateCondition)
	{
		return myMessageDao.deleteMessageByDate(dateCondition);
	}
	
	@Override
	public Integer count(MyMessage entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MyMessage> select(MyMessage entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
