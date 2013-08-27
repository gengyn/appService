package com.qingzhou.app.client.dao;

import java.util.List;

import com.qingzhou.app.client.domain.ProjectPhoto;
import com.qingzhou.app.client.domain.ProjectPlanDetail;
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
	public List<ProjectPhoto> listPhoto(ProjectPhoto projectPhoto);

}
