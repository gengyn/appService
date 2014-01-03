package com.qingzhou.app.client.dao;

import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.MainDetail;
import com.qingzhou.app.client.domain.ProjectPhoto;
import com.qingzhou.app.client.domain.ProjectPlanDetail;
import com.qingzhou.app.client.domain.RestProjectPhoto;
import com.qingzhou.app.client.domain.BaseDetail;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface ProjectPlanDao {

	/**
	 * 查询进度明细
	 * @param ProjectPlanDetail
	 * @return
	 */
	public List<ProjectPlanDetail> listProjectPlan(ProjectPlanDetail projectPlanDetail);
	/**
	 * 查询照片列表
	 * @param projectPhoto
	 * @return
	 */
	public List<RestProjectPhoto> listPhoto(ProjectPhoto projectPhoto);
	/**
	 * 超出工期的天数，用于判断工程状态
	 * @param projectPlanDetail
	 * @return
	 */
	public int getPassDay(ProjectPlanDetail projectPlanDetail);
	/**
	 * 当前离开工日的天数，用于判断工程状态
	 * @param projectPlanDetail
	 * @return
	 */
	public int getLeadDay(ProjectPlanDetail projectPlanDetail);
	
	/**
	 * 查询基础明细
	 * @param baseDetail
	 * @return
	 */
	public List<BaseDetail>listBaseDetail(Map<String,String> conditionMap);
	/**
	 * 查询主材明细
	 * @param conditionMap
	 * @return
	 */
	public List<MainDetail>listMainDetail(Map<String,String> conditionMap);

}
