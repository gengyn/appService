package com.qingzhou.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.core.dao.SyscodeTypeDao;
import com.qingzhou.core.domain.SyscodeType;
import com.qingzhou.core.service.BaseService;

@Service
public class SyscodeTypeService extends BaseService<SyscodeType> {
	@Autowired
	private SyscodeTypeDao syscodeTypeDao;

	@Override
	public Integer count(SyscodeType entity) {
		return syscodeTypeDao.count(entity);
	}

	@Override
	public List<SyscodeType> select(SyscodeType entity) {
		return syscodeTypeDao.select(entity);
	}
	
	public List<SyscodeType> getConfSyscodeTypeList(String[] array){
		return syscodeTypeDao.getConfSyscodeTypeList(array);
	}
}
