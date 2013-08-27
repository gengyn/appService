package com.qingzhou.app.client.domain;

import java.util.List;

public class ProjectPlanDetail {

	private String customer_id;//客户ID
	
	private String schedetail_id;	//	施工进度明细ID
	private String schedetail_flag;	//	是否完成
	private String sche_id;	//	工程ID
	private String sche_start_project;	//	开始时间
	private String sche_end_project;	//	完成时间
	private String sche_days;	//	工期
	private String project_process_id;	//	进程ID
	private String project_process_name;	//	进程名称
	private String project_process_level;	//	进程级别
	private String project_process_order;	//进程排序
	
	private String project_process_parent_id;//当进程级别为2时，上级进程ID
	private String curr_date;//当前日期
	
	private String is_last_done;	//	是否为最后完成状态（0：不是；1：是）
	private int photocount;	//	照片数量
	
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getSchedetail_id() {
		return schedetail_id;
	}
	public void setSchedetail_id(String schedetail_id) {
		this.schedetail_id = schedetail_id;
	}
	public String getSchedetail_flag() {
		return schedetail_flag;
	}
	public void setSchedetail_flag(String schedetail_flag) {
		this.schedetail_flag = schedetail_flag;
	}
	public String getSche_id() {
		return sche_id;
	}
	public void setSche_id(String sche_id) {
		this.sche_id = sche_id;
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
	public String getSche_days() {
		return sche_days;
	}
	public void setSche_days(String sche_days) {
		this.sche_days = sche_days;
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
	public String getProject_process_level() {
		return project_process_level;
	}
	public void setProject_process_level(String project_process_level) {
		this.project_process_level = project_process_level;
	}
	public String getIs_last_done() {
		return is_last_done;
	}
	public void setIs_last_done(String is_last_done) {
		this.is_last_done = is_last_done;
	}
	public int getPhotocount() {
		return photocount;
	}
	public void setPhotocount(int photocount) {
		this.photocount = photocount;
	}
	public String getProject_process_parent_id() {
		return project_process_parent_id;
	}
	public void setProject_process_parent_id(String project_process_parent_id) {
		this.project_process_parent_id = project_process_parent_id;
	}
	public String getCurr_date() {
		return curr_date;
	}
	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}
	public String getProject_process_order() {
		return project_process_order;
	}
	public void setProject_process_order(String project_process_order) {
		this.project_process_order = project_process_order;
	}

}
