package com.qingzhou.app.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.Aircraft;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface AircraftDao {

	/**
	 * 分页查询航空器性能信息
	 * 
	 * @param args
	 * @return
	 */
	public List<Aircraft> listAircraft(Aircraft aircraft);

	/**
	 * 分页查询航空器性能信息总记录数
	 * 
	 * @param args
	 * @return
	 */
	public int sumListAircraft(Aircraft aircraft);

	/**
	 * 返回航空器性能信息具体信息
	 */
	public Aircraft selectByPK(Aircraft aircraft); 
	
	/**
	 * 新增航空器性能信息
	 * 
	 * @param Aircraft
	 * @return
	 */
	public Integer insertAircraft(Aircraft aircraft);

	/**
	 * 修改航空器性能信息
	 * 
	 * @param aircraft
	 * @return
	 */
	public Integer updateAircraft(Aircraft aircraft);
	
	/**
	 * 删除航空器性能信息(多)
	 * 
	 * @param array
	 * @return
	 */
	public Integer deleteAircraftArray(String[] array);

}
