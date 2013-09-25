package com.qingzhou.app.client.domain;

public class Notice {

		
	private int target_flag = 0;//目标标识，发送给客户还是员工,0为客户
	private String alias;//别名
	private String tag;//分组
	private int notice_type;//通知分类
	
	private String notice_title;//通知标题，非栏目性通知使用
	private String notice_content;//通知内容，非栏目性通知使用
	private String notice_url;//通知连接的具体内容，非栏目性通知使用
	
	private int counts = 0;//通知中有关数量
	private String msg_sender; //如果是我的消息，需要填写发送人是谁
	private String msg_sendername;//发送人人名
	
	public String getNotice_url() {
		return notice_url;
	}
	public void setNotice_url(String notice_url) {
		this.notice_url = notice_url;
	}
	
	public String getMsg_sender() {
		return msg_sender;
	}
	public void setMsg_sender(String msg_sender) {
		this.msg_sender = msg_sender;
	}
	public String getMsg_sendername() {
		return msg_sendername;
	}
	public void setMsg_sendername(String msg_sendername) {
		this.msg_sendername = msg_sendername;
	}
	
	
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(int notice_type) {
		this.notice_type = notice_type;
	}
	public int getTarget_flag() {
		return target_flag;
	}
	public void setTarget_flag(int target_flag) {
		this.target_flag = target_flag;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
