package com.qingzhou.core.domain;


/**
 * 登陆用户类
 * 用于保存登陆用户的相关属性
 */
public class LoginUser {
	
	/**
	 * 用户id
	 */
	private String 	 userId;
	
	public LoginUser() {
		super();
	}
	
	public LoginUser(String userId, String seatSoftId, String seatId,
			String roleId,String userName) {
		super();
		this.userId = userId;
		this.seatSoftId = seatSoftId;
		this.seatId = seatId;
		this.roleId = roleId;
		this.userName=userName;
	}
	/**
	 * 席位应用软件id
	 */
	private String 	 seatSoftId;
	
	/**
	 * 席位id
	 */
	private String 	 seatId;
	
	/**
	 * 角色id
	 */
	private String 	 roleId;
	
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSeatSoftId() {
		return seatSoftId;
	}
	public void setSeatSoftId(String seatSoftId) {
		this.seatSoftId = seatSoftId;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
