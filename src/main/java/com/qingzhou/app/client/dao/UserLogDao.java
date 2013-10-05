package com.qingzhou.app.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.UserBase;
import com.qingzhou.app.client.domain.LoginStatus;
import com.qingzhou.app.client.domain.UserLog;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface UserLogDao {
	
	/**
	 * 写入操作日志
	 * @param userLog
	 */
	public void insertOPLog(UserLog userLog);
	
	/**
	 * 删除操作日志
	 * @param dateCondition
	 */
	public void delOPLog(int dateCondition);
	

}
