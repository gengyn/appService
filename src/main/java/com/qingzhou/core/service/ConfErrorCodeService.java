package com.qingzhou.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.core.dao.ConfErrorCodeDao;
import com.qingzhou.core.domain.ConfErrorCode;
import com.qingzhou.core.service.BaseService;

@Service("confErrorCodeService")
public class ConfErrorCodeService extends BaseService<ConfErrorCode> {
	@Autowired
	private ConfErrorCodeDao confErrorCodeDao;

	public Integer create(ConfErrorCode confErrorCode) {
		return confErrorCodeDao.insert(confErrorCode);
	}

	public Integer modify(ConfErrorCode confErrorCode) {
		return confErrorCodeDao.updateByPK(confErrorCode);
	}

	public Integer remove(ConfErrorCode confErrorCode) {
		return confErrorCodeDao.delete(confErrorCode);
	}
	
	public Integer removeBatch(String[] array){
		return confErrorCodeDao.deleteBatch(array);
	}
	
	public List<ConfErrorCode> listConfErrorCode(ConfErrorCode confErrorCode)
	{
		return confErrorCodeDao.listConfErrorCode(confErrorCode);
	}

	@Override
	public Integer count(ConfErrorCode entity) {
		return confErrorCodeDao.count(entity);
	}

	@Override
	public List<ConfErrorCode> select(ConfErrorCode entity) {
		return confErrorCodeDao.select(entity);
	}
}
