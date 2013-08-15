package com.qingzhou.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.qingzhou.core.domain.Page;

/**
 * mapping for CONF_PROMPT_MESSAGE
 * meaning 
 * @author CodeGenerator
 */
public class ConfPromptMessage extends Page<ConfPromptMessage> {

	/**
	 * mapping for CONF_PROMPT_MESSAGE.ID
	 * meaning 
	 */
	private String id;

	/**
	 * mapping for CONF_PROMPT_MESSAGE.TEXT
	 * meaning 
	 */
	private String text;

	/**
	 * mapping for CONF_PROMPT_MESSAGE.REMARK
	 * meaning 
	 */
	private String remark;

	/**
	 * get CONF_PROMPT_MESSAGE.ID
	 * @return 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set CONF_PROMPT_MESSAGE.ID
	 * @param 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get CONF_PROMPT_MESSAGE.TEXT
	 * @return 
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * set CONF_PROMPT_MESSAGE.TEXT
	 * @param 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * get CONF_PROMPT_MESSAGE.REMARK
	 * @return 
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set CONF_PROMPT_MESSAGE.REMARK
	 * @param 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
