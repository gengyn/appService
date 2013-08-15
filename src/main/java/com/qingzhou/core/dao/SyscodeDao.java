package com.qingzhou.core.dao;

import java.util.List;


import com.qingzhou.core.dao.MyBatisDao;
import com.qingzhou.core.domain.Syscode;

@MyBatisDao
public interface SyscodeDao {
	public List<Syscode> listConfSyscode(Syscode confSyscode);
	public List<Syscode> select(Syscode confSyscode);
	public Integer count(Syscode confSyscode);
	public Syscode selectByPK(String id);
	public Integer deleteByPK(String id);
	public Integer updateByPK(Syscode confSyscode);
	public Integer insert(Syscode confSyscode);
	public Integer delete(Syscode confSyscode);
	public Integer deleteBatch(String[] array);
	public List<Syscode> getConfSyscodeList(String[] array);
}
