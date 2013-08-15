package com.qingzhou.core.domain;

import java.sql.Date;

import com.qingzhou.core.domain.Page;

/**
 * mapping for CONF_SYSCODE
 * meaning 
 * @author CodeGenerator
 */
public class Syscode extends Page<Syscode> {

	private String sysCodeTypeInfo;
	private String objectName;
	
	public String getSysCodeTypeInfo() {
		return sysCodeTypeInfo;
	}

	public void setSysCodeTypeInfo(String sysCodeTypeInfo) {
		this.sysCodeTypeInfo = sysCodeTypeInfo;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * mapping for CONF_SYSCODE.ID
	 * meaning 
	 */
	private String id;

	/**
	 * mapping for CONF_SYSCODE.TYPE_ID
	 * meaning 
	 */
	private String typeId;

	/**
	 * mapping for CONF_SYSCODE.NAME
	 * meaning 
	 */
	private String name;

	/**
	 * mapping for CONF_SYSCODE.VALUE
	 * meaning 
	 */
	private String value;

	/**
	 * mapping for CONF_SYSCODE.CREATE_TIME
	 * meaning 
	 */
	private Date createTime;

	/**
	 * mapping for CONF_SYSCODE.CREATEUSER_ID
	 * meaning 
	 */
	private String createuserId;

	/**
	 * mapping for CONF_SYSCODE.MODIF_TIME
	 * meaning 
	 */
	private Date modifTime;

	/**
	 * mapping for CONF_SYSCODE.USER_ID
	 * meaning 
	 */
	private String userId;

	/**
	 * get CONF_SYSCODE.ID
	 * @return 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set CONF_SYSCODE.ID
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get CONF_SYSCODE.TYPE_ID
	 * @return 
	 */
	public String getTypeId() {
		return this.typeId;
	}

	/**
	 * set CONF_SYSCODE.TYPE_ID
	 * @param 
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * get CONF_SYSCODE.NAME
	 * @return 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set CONF_SYSCODE.NAME
	 * @param 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get CONF_SYSCODE.VALUE
	 * @return 
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * set CONF_SYSCODE.VALUE
	 * @param 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * get CONF_SYSCODE.CREATE_TIME
	 * @return 
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * set CONF_SYSCODE.CREATE_TIME
	 * @param 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get CONF_SYSCODE.CREATEUSER_ID
	 * @return 
	 */
	public String getCreateuserId() {
		return this.createuserId;
	}

	/**
	 * set CONF_SYSCODE.CREATEUSER_ID
	 * @param 
	 */
	public void setCreateuserId(String createuserId) {
		this.createuserId = createuserId;
	}

	/**
	 * get CONF_SYSCODE.MODIF_TIME
	 * @return 
	 */
	public Date getModifTime() {
		return this.modifTime;
	}

	/**
	 * set CONF_SYSCODE.MODIF_TIME
	 * @param 
	 */
	public void setModifTime(Date modifTime) {
		this.modifTime = modifTime;
	}

	/**
	 * get CONF_SYSCODE.USER_ID
	 * @return 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set CONF_SYSCODE.USER_ID
	 * @param 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
