package com.qingzhou.app.client.dao;

import java.util.List;

import com.qingzhou.app.client.domain.Version;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface VersionDao {

	/**
	 * 列表返回版本记录
	 * 
	 * @param args
	 * @return
	 */
	public List<Version> listVersion(Version version);

	
	/**
	 * 返回最新的版本信息
	 */
	public Version getLastVer(Version version); 
	
	

}
