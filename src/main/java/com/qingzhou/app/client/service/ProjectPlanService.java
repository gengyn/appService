package com.qingzhou.app.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.RestCommonDao;
import com.qingzhou.app.client.dao.ProjectPlanDao;
import com.qingzhou.app.client.domain.ProjectPlanDetail;
import com.qingzhou.app.client.domain.ProjectPhoto;
import com.qingzhou.app.client.domain.RestProjectPhoto;
import com.qingzhou.app.client.domain.RestProjectPlan;
import com.qingzhou.app.client.domain.RestProjectPlanDetail;
import com.qingzhou.core.service.BaseService;
import com.qingzhou.core.util.LogicUtils;

@Service
public class ProjectPlanService  extends BaseService {

	@Autowired
	private ProjectPlanDao projectPlanDao;	
	@Autowired
	private RestCommonDao restCommonDao;
	String currDate = "";//当前日期
	String customer_id = "";//客户号
	String sche_id = "";
	//工程状态
	static int PROJECT_NORMAL = 0x11; //正常
	static int PROJECT_DEFER = 0x12;	 //延误
	static int PROJECT_FINISH = 0x13; //竣工
		
		//进程状态W
	static int PROCESS_NOSTART = 0x21;//未开始
	static int PROCESS_GOING = 0x22;//进行中	
	static int PROCESS_END = 0x23; //完工

	public void setProjectPlanDao(ProjectPlanDao projectPlanDao) {
		this.projectPlanDao = projectPlanDao;
	}
	
	public void setRestCommonDao(RestCommonDao restCommonDao) {
		this.restCommonDao = restCommonDao;
	}
	/**
	 * 返回工程进度信息
	 * @param customer_id
	 * @return
	 */
	public RestProjectPlan getProjectPlan(String customer_id)
	{
		RestProjectPlan projectPlan = new RestProjectPlan();
		currDate = restCommonDao.getCurDate();
		if (LogicUtils.isEmpty(customer_id))
			return projectPlan;
		else this.customer_id = customer_id;
		//查询
		ProjectPlanDetail projectPlanDetail = new ProjectPlanDetail();
		projectPlanDetail.setCustomer_id(customer_id);
		projectPlanDetail.setProject_process_level("1");
		//第一级
		List<ProjectPlanDetail> level1List = projectPlanDao.listProjectPlan(projectPlanDetail);
		//第二级
		projectPlanDetail.setProject_process_level("2");
		List<ProjectPlanDetail> level2List = projectPlanDao.listProjectPlan(projectPlanDetail);
		//获取工程当前阶段
		//ProjectPlanDetail cur_ppd = getCurrPlan(level1List);
		int currID = getCurrPlanID(level1List);
		//进程列表
		List<RestProjectPlanDetail> restProjectPlanDetail = new ArrayList<RestProjectPlanDetail>();
		projectPlan.setProjectPlanDetailList(restProjectPlanDetail);
		
		if (currID == -1)
		{
			projectPlan.setCurrID(level2List.size()-1);
			projectPlan.setCurrPlanName("已竣工");
			projectPlan.setPlanStatus(this.PROJECT_FINISH);
		}else
		{
			projectPlan.setCurrID(currID);
			projectPlan.setCurrPlanName(level1List.get(currID).getProject_process_name());
			projectPlan.setPlanStatus(getPlanStatus(level1List.get(currID)));
		}
		
		for(ProjectPlanDetail level1ppd:level1List)
		{
			//ProjectPlanDetail level1ppd = level1List.get(i);
			RestProjectPlanDetail rppd = new RestProjectPlanDetail();
			rppd.setSchedetail_id(level1ppd.getSchedetail_id());
			rppd.setProject_process_id(level1ppd.getProject_process_id());
			rppd.setProject_process_name(level1ppd.getProject_process_name());
			rppd.setSche_start_project(level1ppd.getSche_start_project());
			rppd.setSche_end_project(level1ppd.getSche_end_project());
			rppd.setPhotocount(level1ppd.getPhotocount());
			sche_id = level1ppd.getSche_id();
			if (currID == -1 || !LogicUtils.isEmpty(level1ppd.getSchedetail_flag()))
				rppd.setProject_process_status(this.PROCESS_END);
			else if (level1ppd.getSchedetail_id().equals(level1List.get(currID).getSchedetail_id()))
				rppd.setProject_process_status(this.PROCESS_GOING);
			else rppd.setProject_process_status(this.PROCESS_NOSTART);
			
			List<RestProjectPlanDetail> subList = new ArrayList<RestProjectPlanDetail>();
			Iterator<ProjectPlanDetail> level2Iterator = level2List.listIterator();
			while(level2Iterator.hasNext())
			{
				ProjectPlanDetail level2ppd = level2Iterator.next();
				
				if (level2ppd.getProject_process_parent_id() != null && 
						level2ppd.getProject_process_parent_id().equals(level1ppd.getProject_process_id()))
				{

					RestProjectPlanDetail level2rppd = new RestProjectPlanDetail();
					level2rppd.setSchedetail_id(level2ppd.getSchedetail_id());
					level2rppd.setProject_process_id(level2ppd.getProject_process_id());
					level2rppd.setProject_process_name(level2ppd.getProject_process_name());
					level2rppd.setSche_start_project(level2ppd.getSche_start_project()==null?"":level2ppd.getSche_start_project());
					level2rppd.setSche_end_project(level2ppd.getSche_end_project()==null?"":level2ppd.getSche_end_project());
					level2rppd.setPhotocount(level2ppd.getPhotocount());
					if (!LogicUtils.isEmpty(level2ppd.getSchedetail_flag()))
						level2rppd.setProject_process_status(this.PROCESS_END);
					else level2rppd.setProject_process_status(this.PROCESS_NOSTART);
					level2Iterator.remove();
					
					subList.add(level2rppd);
				}
			}
			
			rppd.setRestProjectPlanDetailList(subList);
			restProjectPlanDetail.add(rppd);
			projectPlan.setSche_id(sche_id);
		}
	
		return projectPlan;
	}
	
	/**
	 * 获取工程当前阶段
	 * @param ppdList
	 * @return
	 */
	private ProjectPlanDetail getCurrPlan(List<ProjectPlanDetail> ppdList)
	{
		ProjectPlanDetail returnPpd = new ProjectPlanDetail();
		for(int i=0;i<ppdList.size();i++)
		{
			ProjectPlanDetail ppd = ppdList.get(i);
			
			if (LogicUtils.isEmpty(ppd.getSchedetail_flag()))
			{
				if (ppd.getProject_process_level().equals("1"))
				{
					returnPpd = ppd;
					break;
				}
			}
		}
		return returnPpd;
	}
	
	/**
	 * 获取工程当前阶段ID
	 * @param ppdList
	 * @return
	 */
	private int getCurrPlanID(List<ProjectPlanDetail> ppdList)
	{
		int id = -1;
		for(int i=0;i<ppdList.size();i++)
		{
			ProjectPlanDetail ppd = ppdList.get(i);
			
			if (LogicUtils.isEmpty(ppd.getSchedetail_flag()))
			{
				if (ppd.getProject_process_level().equals("1"))
				{
					id = i;
					break;
				}
			}
		}
		return id;
	}
	
	/**
	 * 判断工程是否延期
	 * @param currDate
	 * @return
	 */
	private int getPlanStatus(ProjectPlanDetail ppd)
	{
		int planStatus = this.PROJECT_DEFER;
		ProjectPlanDetail projectPlanDetail = new ProjectPlanDetail();
		projectPlanDetail.setCustomer_id(customer_id);
		projectPlanDetail.setCurr_date(currDate);
		projectPlanDetail.setSche_id(ppd.getSche_id());
		projectPlanDetail.setProject_process_level("1");
		
		int days = projectPlanDao.getPassDay(projectPlanDetail);
		if (days > 0) return this.PROJECT_DEFER;
		days = projectPlanDao.getLeadDay(projectPlanDetail);
		if (days > 0) return this.PROJECT_NORMAL;
		
		
		List<ProjectPlanDetail> ppdList = projectPlanDao.listProjectPlan(projectPlanDetail);
		
		if (ppdList.size()>0)
		{
			ProjectPlanDetail temp = ppdList.get(0);
			//当实际进度>=计划进度    为正常，否则为延期
			if (Integer.parseInt(temp.getProject_process_order()) <= Integer.parseInt(ppd.getProject_process_order()))
				planStatus = this.PROJECT_NORMAL;
			else planStatus = this.PROJECT_DEFER;
		}
		
		return planStatus;
	}
	
	/**
	 * 返回照片列表
	 * @param schedetail_id
	 * @param sche_id
	 * @return
	 */
	public List<RestProjectPhoto> getPhoto(String schedetail_id,String sche_id)
	{
		ProjectPhoto projectPhoto = new ProjectPhoto();
		projectPhoto.setSche_detail_id(schedetail_id);
		projectPhoto.setSche_id(sche_id);
		
		return projectPlanDao.listPhoto(projectPhoto);
	}
	
	

	@Override
	public Integer count(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List select(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
