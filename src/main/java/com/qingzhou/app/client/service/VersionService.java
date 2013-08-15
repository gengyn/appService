package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.VersionDao;
import com.qingzhou.app.client.domain.Version;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;

@Service
public class VersionService  extends BaseService<Version> {

	@Autowired
	private VersionDao versionDao;	

	public void setAircraftDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}
	
	@Override
	public List<Version> select(Version version) {
		List<Version> list; 
		list = versionDao.listVersion(version);
		return list;
	}
	
	public Version getLastVer(Version version)
	{
		return versionDao.getLastVer(version);
	}

	@Override
	public Integer count(Version entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
