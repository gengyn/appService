package com.qingzhou.core.dao;

import java.util.List;
import com.qingzhou.core.dao.MyBatisDao;
import com.qingzhou.core.domain.ConfPromptMessage;

@MyBatisDao
public interface ConfPromptMessageDao {
	public List<ConfPromptMessage> listConfPromptMessage(ConfPromptMessage confPromptMessage);
	public List<ConfPromptMessage> select(ConfPromptMessage confPromptMessage);
	public Integer count(ConfPromptMessage confPromptMessage);
	public ConfPromptMessage selectByPK(String id);
	public Integer deleteByPK(String id);
	public Integer updateByPK(ConfPromptMessage confPromptMessage);
	public Integer insert(ConfPromptMessage confPromptMessage);
	public Integer delete(ConfPromptMessage confPromptMessage);
	public Integer deleteBatch(String[] array);
}
