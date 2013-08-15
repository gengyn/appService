package com.qingzhou.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.qingzhou.core.domain.Page;

/**
 * mapping for CONF_ERROR_CODE
 * meaning 
 * @author CodeGenerator
 */
public class ConfErrorCode extends Page<ConfErrorCode> {

	/**
	 * mapping for CONF_ERROR_CODE.ID
	 * meaning 
	 */
	private String id;

	/**
	 * mapping for CONF_ERROR_CODE.MESSAGE
	 * meaning 
	 */
	private String message;

	/**
	 * mapping for CONF_ERROR_CODE.REMARK
	 * meaning 
	 */
	private String remark;

	/**
	 * get CONF_ERROR_CODE.ID
	 * @return 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set CONF_ERROR_CODE.ID
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get CONF_ERROR_CODE.MESSAGE
	 * @return 
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * set CONF_ERROR_CODE.MESSAGE
	 * @param 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * get CONF_ERROR_CODE.REMARK
	 * @return 
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set CONF_ERROR_CODE.REMARK
	 * @param 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
