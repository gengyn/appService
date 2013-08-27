package com.qingzhou.app.client.domain;

public class ProjectPhoto {
	private String file_path;//图片路径
	
	private String sche_id;//工程ID
	private String sche_detail_id;//进度ID
	String path = "/uploadFile";
	
	public String getFile_path() {
		return path+file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public String getSche_id() {
		return sche_id;
	}
	public void setSche_id(String sche_id) {
		this.sche_id = sche_id;
	}
	public String getSche_detail_id() {
		return sche_detail_id;
	}
	public void setSche_detail_id(String sche_detail_id) {
		this.sche_detail_id = sche_detail_id;
	}
}
