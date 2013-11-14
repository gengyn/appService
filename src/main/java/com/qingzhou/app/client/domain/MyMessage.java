package com.qingzhou.app.client.domain;

public class MyMessage {
	private String user_token;
	private int message_id;
	private String sender;
	private String sender_name;
	private String receiver;
	private String message_type;
	private String message_title;
	private String message_content;
	private String send_time;
	private String last_send_time;
	private String img_url;
	private String voice_url;
	private int target_flag = 0;//目标标识，发送给客户还是员工,0为客户
	private String issucceed;//0为成功，1为失败
	
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	public String getIssucceed() {
		return issucceed;
	}
	public void setIssucceed(String issucceed) {
		this.issucceed = issucceed;
	}
	public int getTarget_flag() {
		return target_flag;
	}
	public void setTarget_flag(int target_flag) {
		this.target_flag = target_flag;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getVoice_url() {
		return voice_url;
	}
	public void setVoice_url(String voice_url) {
		this.voice_url = voice_url;
	}
	public String getLast_send_time() {
		return last_send_time;
	}
	public void setLast_send_time(String last_send_time) {
		this.last_send_time = last_send_time;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

}
