package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.InfoDao;
import com.qingzhou.app.client.domain.Myinfo;
import com.qingzhou.core.service.BaseService;

@Service
public class MyinfoService  extends BaseService<InfoDao> {

	@Autowired
	private InfoDao infoDao;	
	

	public void setLoginStatusDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}
	
	/**
	 * 返回资讯列表
	 * @param customer_id
	 * @return
	 */
	public List<Myinfo> listInfo(String customer_id,String pageNo,String pageSize)
	{
		Map<String,String> whereMap = new HashMap<String,String>();	
		whereMap.put("customer_id", customer_id);
		whereMap.put("pageNo", pageNo+"");
		whereMap.put("pageSize", pageSize+"");
		return infoDao.listInfo(whereMap);
	}

	@Override
	public Integer count(InfoDao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfoDao> select(InfoDao entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
