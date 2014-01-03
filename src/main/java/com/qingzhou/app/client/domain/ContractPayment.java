package com.qingzhou.app.client.domain;

public class ContractPayment {
	String contractMoney;//应缴金额
	String payMoney;//已缴金额
	String noPayMoney;//未缴金额
	String gatItem;
	String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGatItem() {
		return gatItem;
	}
	public void setGatItem(String gatItem) {
		this.gatItem = gatItem;
	}
	public String getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getNoPayMoney() {
		return noPayMoney;
	}
	public void setNoPayMoney(String noPayMoney) {
		this.noPayMoney = noPayMoney;
	}
}
