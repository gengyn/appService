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
	//工程状态
	static String PROJECT_NORMAL = "normal"; //正常
	static String PROJECT_DEFER = "defer";	 //延误
	static String PROJECT_FINISH = "finish"; //竣工
	
	//进程状态
	static String PROCESS_NOSTART = "0";//未开始
	static String PROCESS_GOING = "1";//进行中	
	static String PROCESS_END = "2"; //完工

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
		projectPlanDetail.setProject_process_level("2");
		//第二级
		List<ProjectPlanDetail> level2List = projectPlanDao.listProjectPlan(projectPlanDetail);
		//获取工程当前阶段
		ProjectPlanDetail cur_ppd = getCurrPlan(level1List);
		//进程列表
		List<RestProjectPlanDetail> restProjectPlanDetail = new ArrayList<RestProjectPlanDetail>();
		projectPlan.setProjectPlanDetailList(restProjectPlanDetail);
		if (cur_ppd == null)
		{
			projectPlan.setCurrPlanName("已竣工");
			projectPlan.setPlanStatus(this.PROJECT_FINISH);
		}else
		{
			projectPlan.setCurrPlanName(cur_ppd.getProject_process_name());
			projectPlan.setPlanStatus(getPlanStatus(cur_ppd));
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
			if (cur_ppd == null || !LogicUtils.isEmpty(level1ppd.getSchedetail_flag()))
				rppd.setProject_process_status(this.PROCESS_END);
			else if (level1ppd.getSchedetail_id().equals(cur_ppd.getSchedetail_id()))
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
	 * 判断工程是否延期
	 * @param currDate
	 * @return
	 */
	private String getPlanStatus(ProjectPlanDetail ppd)
	{
		String planStatus = "";
		ProjectPlanDetail projectPlanDetail = new ProjectPlanDetail();
		projectPlanDetail.setCustomer_id(customer_id);
		projectPlanDetail.setCurr_date(currDate);
		projectPlanDetail.setProject_process_level("1");
		List<ProjectPlanDetail> ppdList = projectPlanDao.listProjectPlan(projectPlanDetail);
		
		ProjectPlanDetail temp = ppdList.get(0);
		//当实际进度>=计划进度    为正常，否则为延期
		if (Integer.parseInt(temp.getProject_process_order()) <= Integer.parseInt(ppd.getProject_process_order()))
			planStatus = this.PROJECT_NORMAL;
		else planStatus = this.PROJECT_DEFER;
		
		return planStatus;
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
