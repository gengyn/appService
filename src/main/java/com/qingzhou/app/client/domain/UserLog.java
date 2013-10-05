package com.qingzhou.app.client.domain;

public class UserLog {

	private String user_token;
	private String log_id;
	private String op_id;
	private String op_mobile;
	private String op_name;
	private String op_time;
	private String op_content;
	private String op_result;
	
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getOp_id() {
		return op_id;
	}
	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}
	public String getOp_mobile() {
		return op_mobile;
	}
	public void setOp_mobile(String op_mobile) {
		this.op_mobile = op_mobile;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getOp_content() {
		return op_content;
	}
	public void setOp_content(String op_content) {
		this.op_content = op_content;
	}
	public String getOp_result() {
		return op_result;
	}
	public void setOp_result(String op_result) {
		this.op_result = op_result;
	}
}
