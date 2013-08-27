package com.qingzhou.app.client.domain;

import java.util.List;


public class RestProjectPlan {

	private String currPlanName;//当前进度名称
	private String planStatus;//工程状态，normal正常  defer延期  finish竣工 
	
	private List<RestProjectPlanDetail> projectPlanDetailList; //项目明细

	public String getCurrPlanName() {
		return currPlanName;
	}

	public void setCurrPlanName(String currPlanName) {
		this.currPlanName = currPlanName;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public List<RestProjectPlanDetail> getProjectPlanDetailList() {
		return projectPlanDetailList;
	}

	public void setProjectPlanDetailList(
			List<RestProjectPlanDetail> projectPlanDetailList) {
		this.projectPlanDetailList = projectPlanDetailList;
	}
		
}
