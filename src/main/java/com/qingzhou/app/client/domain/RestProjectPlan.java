package com.qingzhou.app.client.domain;

import java.util.List;


public class RestProjectPlan {

	private String currPlanName;//当前进度名称
	private int currID;//当前进度序号
	private int planStatus;//工程状态，normal正常  defer延期  finish竣工 
	private String sche_id;//工程ID
	private String base_detail_value;//基础明细
	private String main_detail_value;//主材明细
	private String time_limit;//工期

	public String getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(String time_limit) {
		this.time_limit = time_limit;
	}

	public String getBase_detail_value() {
		return base_detail_value;
	}

	public void setBase_detail_value(String base_detail_value) {
		this.base_detail_value = base_detail_value;
	}

	public String getMain_detail_value() {
		return main_detail_value;
	}

	public void setMain_detail_value(String main_detail_value) {
		this.main_detail_value = main_detail_value;
	}

	private List<RestProjectPlanDetail> projectPlanDetailList; //项目明细

	public int getCurrID() {
		return currID;
	}

	public void setCurrID(int currID) {
		this.currID = currID;
	}
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
