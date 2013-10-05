package com.qingzhou.app.client.dao;

import java.util.List;

import com.qingzhou.app.client.domain.MyMessage;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface MyMessageDao {


	public List<MyMessage> listMessage(MyMessage message);

	public Integer insertMessage(MyMessage message);
	
	public Integer updateMessage(MyMessage message);
	
	public Integer deleteMessageByDate(int dateCondition);

}
