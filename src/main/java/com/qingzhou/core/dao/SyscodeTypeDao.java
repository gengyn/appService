package com.qingzhou.core.dao;

import java.util.List;

import com.qingzhou.core.dao.MyBatisDao;
import com.qingzhou.core.domain.SyscodeType;

@MyBatisDao
public interface SyscodeTypeDao {
	public List<SyscodeType> listConfSyscodeType(SyscodeType confSyscodeType);
	public List<SyscodeType> select(SyscodeType confSyscodeType);
	public Integer count(SyscodeType confSyscodeType);
	public SyscodeType selectByPK(String typeId);
	public Integer deleteByPK(String typeId);
	public Integer updateByPK(SyscodeType confSyscodeType);
	public Integer insert(SyscodeType confSyscodeType);
	public Integer delete(SyscodeType confSyscodeType);
	public Integer deleteBatch(String[] array);
	public List<SyscodeType> getConfSyscodeTypeList(String[] array);
}
