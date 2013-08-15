package com.qingzhou.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.core.dao.ConfPromptMessageDao;
import com.qingzhou.core.domain.ConfPromptMessage;
import com.qingzhou.core.service.BaseService;


@Service("confPromptMessageService")
public class ConfPromptMessageService extends BaseService<ConfPromptMessage> {
	
	@Autowired
	private ConfPromptMessageDao confPromptMessageDao;

	public List<ConfPromptMessage> listConfPromptMessage(ConfPromptMessage confPromptMessage)
	{
		return confPromptMessageDao.listConfPromptMessage(confPromptMessage);
	}
	
	public Integer create(ConfPromptMessage confPromptMessage) {
		return confPromptMessageDao.insert(confPromptMessage);
	}

	public Integer modify(ConfPromptMessage confPromptMessage) {
		return confPromptMessageDao.updateByPK(confPromptMessage);
	}

	public Integer remove(ConfPromptMessage confPromptMessage) {
		return confPromptMessageDao.delete(confPromptMessage);
	}
	
	public Integer removeBatch(String[] array){
		return confPromptMessageDao.deleteBatch(array);
	}

	@Override
	public Integer count(ConfPromptMessage entity) {
		return confPromptMessageDao.count(entity);
	}

	@Override
	public List<ConfPromptMessage> select(ConfPromptMessage entity) {
		return confPromptMessageDao.select(entity);
	}
}
