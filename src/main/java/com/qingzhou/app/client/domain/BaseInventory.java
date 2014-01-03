package com.qingzhou.app.client.domain;

public class BaseInventory {
	
	private String basName;//项目名称
	private String basUnit;//单位
	private String basePrice;//单价
	private String budgetCount;//合同数量
	private String baseCount;//增减数量
	private String contracePrice;//合同金额
	private String changePrice;//变化金额
	private String practicalPrice;//实际金额
	private String flag;//是否优惠，为1是优惠,为2是总计
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getBasName() {
		return basName;
	}
	public void setBasName(String basName) {
		this.basName = basName;
	}
	public String getBasUnit() {
		return basUnit;
	}
	public void setBasUnit(String basUnit) {
		this.basUnit = basUnit;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getBudgetCount() {
		return budgetCount;
	}
	public void setBudgetCount(String budgetCount) {
		this.budgetCount = budgetCount;
	}
	public String getBaseCount() {
		return baseCount;
	}
	public void setBaseCount(String baseCount) {
		this.baseCount = baseCount;
	}
	public String getContracePrice() {
		return contracePrice;
	}
	public void setContracePrice(String contracePrice) {
		this.contracePrice = contracePrice;
	}
	public String getChangePrice() {
		return changePrice;
	}
	public void setChangePrice(String changePrice) {
		this.changePrice = changePrice;
	}
	public String getPracticalPrice() {
		return practicalPrice;
	}
	public void setPracticalPrice(String practicalPrice) {
		this.practicalPrice = practicalPrice;
	}

}
