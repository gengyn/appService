package com.qingzhou.app.client.domain;

import java.util.List;

public class RestProjectPlanDetail {

	
	private String schedetail_id;	//	施工进度明细ID
	private String sche_start_project;	//	开始时间
	private String sche_end_project;	//	完成时间
	private String project_process_id;	//	进程ID
	private String project_process_name;	//	进程名称
	private int project_process_status;//进程状态 nostart未开始  going进行中 end完工
	private int photocount;	//	照片数量
	private String materialMoney;//主材明细
	private String basicMoney;//基础明细
	private String materialState;//主材状态
	private String process_id;
	private String update_time;//更新时间，用于表示完成时间
	
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getMaterialMoney() {
		return materialMoney;
	}
	public void setMaterialMoney(String materialMoney) {
		this.materialMoney = materialMoney;
	}
	public String getBasicMoney() {
		return basicMoney;
	}
	public void setBasicMoney(String basicMoney) {
		this.basicMoney = basicMoney;
	}
	public String getMaterialState() {
		return materialState;
	}
	public void setMaterialState(String materialState) {
		this.materialState = materialState;
	}
	private List<RestProjectPlanDetail> restProjectPlanDetailList;
	
	public List<RestProjectPlanDetail> getRestProjectPlanDetailList() {
		return restProjectPlanDetailList;
	}
	public void setRestProjectPlanDetailList(
			List<RestProjectPlanDetail> restProjectPlanDetailList) {
		this.restProjectPlanDetailList = restProjectPlanDetailList;
	}
	public String getSchedetail_id() {
		return schedetail_id;
	}
	public void setSchedetail_id(String schedetail_id) {
		this.schedetail_id = schedetail_id;
	}
	public String getSche_start_project() {
		return sche_start_project;
	}
	public void setSche_start_project(String sche_start_project) {
		this.sche_start_project = sche_start_project;
	}
	public String getSche_end_project() {
		return sche_end_project;
	}
	public void setSche_end_project(String sche_end_project) {
		this.sche_end_project = sche_end_project;
	}
	public String getProject_process_id() {
		return project_process_id;
	}
	public void setProject_process_id(String project_process_id) {
		this.project_process_id = project_process_id;
	}
	public String getProject_process_name() {
		return project_process_name;
	}
	public void setProject_process_name(String project_process_name) {
		this.project_process_name = project_process_name;
	}

	public int getProject_process_status() {
		return project_process_status;
	}
	public void setProject_process_status(int project_process_status) {
		this.project_process_status = project_process_status;
	}
	public int getPhotocount() {
		return photocount;
	}
	public void setPhotocount(int photocount) {
		this.photocount = photocount;
	}
	
	

}
