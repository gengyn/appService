package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.AircraftDao;
import com.qingzhou.app.client.domain.Aircraft;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;



@Service
public class AircraftService  extends BaseService<Aircraft> {

	@Autowired
	private AircraftDao aircraftDao;	

	public void setAircraftDao(AircraftDao aircraftDao) {
		this.aircraftDao = aircraftDao;
	}
	@Override
	public Integer count(Aircraft aircraft) {
		return aircraftDao.sumListAircraft(aircraft);
	}

	@Override
	public List<Aircraft> select(Aircraft aircraft) {
		List<Aircraft> list; 
		list = aircraftDao.listAircraft(aircraft);
		return list;
	}
	public Aircraft queryByPK(Aircraft aircraft)
	{
		return aircraftDao.selectByPK(aircraft);
	}
	
	public int create(Aircraft aircraft)
	{
		return aircraftDao.insertAircraft(aircraft);
	}
	
	public int modify(Aircraft aircraft)
	{
		return aircraftDao.updateAircraft(aircraft);
	}
	
	public int deleteByPKs(String[] codes)
	{
		return aircraftDao.deleteAircraftArray(codes);
	}
}
