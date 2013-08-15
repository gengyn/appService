package com.qingzhou.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.core.dao.SyscodeDao;
import com.qingzhou.core.domain.Syscode;
import com.qingzhou.core.service.BaseService;

@Service
public class SyscodeService extends BaseService<Syscode> {
	@Autowired
	private SyscodeDao syscodeDao;


	@Override
	public Integer count(Syscode entity) {
		return syscodeDao.count(entity);
	}

	@Override
	public List<Syscode> select(Syscode entity) {
		return syscodeDao.listConfSyscode(entity);
	}
	public List<Syscode> getConfSyscodeList(String[] array){
		return syscodeDao.getConfSyscodeList(array);
	}
}
