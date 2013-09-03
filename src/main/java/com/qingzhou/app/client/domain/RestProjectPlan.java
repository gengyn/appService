package com.qingzhou.app.client.domain;

import java.util.List;


public class RestProjectPlan {

	private String currPlanName;//当前进度名称
	private int planStatus;//工程状态，normal正常  defer延期  finish竣工 
	private String sche_id;//工程ID
	
	private List<RestProjectPlanDetail> projectPlanDetailList; //项目明细

	public String getSche_id() {
		return sche_id;
	}

	public void setSche_id(String sche_id) {
		this.sche_id = sche_id;
	}
	
	public String getCurrPlanName() {
		return currPlanName;
	}

	public void setCurrPlanName(String currPlanName) {
		this.currPlanName = currPlanName;
	}

	public int getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(int planStatus) {
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
