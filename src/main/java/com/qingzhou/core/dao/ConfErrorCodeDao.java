package com.qingzhou.core.dao;

import java.util.List;
import com.qingzhou.core.dao.MyBatisDao;
import com.qingzhou.core.domain.ConfErrorCode;

@MyBatisDao
public interface ConfErrorCodeDao {
	public List<ConfErrorCode> listConfErrorCode(ConfErrorCode confErrorCode);
	public List<ConfErrorCode> select(ConfErrorCode confErrorCode);
	public Integer count(ConfErrorCode confErrorCode);
	public ConfErrorCode selectByPK(String id);
	public Integer deleteByPK(String id);
	public Integer updateByPK(ConfErrorCode confErrorCode);
	public Integer insert(ConfErrorCode confErrorCode);
	public Integer delete(ConfErrorCode confErrorCode);
	public Integer deleteBatch(String[] array);
}
