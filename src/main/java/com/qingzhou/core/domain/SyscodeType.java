package com.qingzhou.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.qingzhou.core.domain.Page;

/**
 * mapping for CONF_SYSCODE_TYPE
 * meaning 
 * @author CodeGenerator
 */
public class SyscodeType extends Page<SyscodeType> {

	/**
	 * mapping for CONF_SYSCODE_TYPE.TYPE_ID
	 * meaning 
	 */
	private String typeId;

	/**
	 * mapping for CONF_SYSCODE_TYPE.CODE_TYPE_NAME
	 * meaning 
	 */
	private String codeTypeName;

	/**
	 * mapping for CONF_SYSCODE_TYPE.CODE_TYPE_VALUE
	 * meaning 
	 */
	private String codeTypeValue;

	/**
	 * get CONF_SYSCODE_TYPE.TYPE_ID
	 * @return 
	 */
	public String getTypeId() {
		return this.typeId;
	}

	/**
	 * set CONF_SYSCODE_TYPE.TYPE_ID
	 * @param 
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * get CONF_SYSCODE_TYPE.CODE_TYPE_NAME
	 * @return 
	 */
	public String getCodeTypeName() {
		return this.codeTypeName;
	}

	/**
	 * set CONF_SYSCODE_TYPE.CODE_TYPE_NAME
	 * @param 
	 */
	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	/**
	 * get CONF_SYSCODE_TYPE.CODE_TYPE_VALUE
	 * @return 
	 */
	public String getCodeTypeValue() {
		return this.codeTypeValue;
	}

	/**
	 * set CONF_SYSCODE_TYPE.CODE_TYPE_VALUE
	 * @param 
	 */
	public void setCodeTypeValue(String codeTypeValue) {
		this.codeTypeValue = codeTypeValue;
	}

}
