package com.qingzhou.app.client.dao;

import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.Myinfo;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface InfoDao {
	
	
	public List<Myinfo> listInfo(Map<String,String> whereMap); 

}
