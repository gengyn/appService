package com.qingzhou.app.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.Aircraft;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface RestCommonDao {

	public String getCurDate();
	
}
