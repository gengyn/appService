package com.qingzhou.app.client.domain;

import java.util.List;
import java.util.Map;

public class Contract {

	private String formal_contract_id; //正式合同主键 
	private String contract_num;//合同编号
	private String contract_type;//合同类型代码
	private String contract_type_name;//合同类型名称
	private String fill_date;//填报日期
	private String contractsigned_date;//合同签订日期
	private String customer_id;//客户ID
	private String reg_name;//客户姓名
	private String reg_phone;//客户手机
	private String reg_village_name;//小区名称
	private String reg_construct_address;//施工地址
	private String reg_customer_mgr;//客户经理代码
	private String reg_customer_mgr_name;//客户经理姓名
	private String reg_customer_mgr_mobile;//客户经理电话
	private String reg_project_mgr;//工程项目经理代码
	private String reg_project_mgr_name;//工程项目经理姓名
	private String reg_project_mgr_mobile;//工程项目经理电话
	private String reg_stylist;//设计师代码
	private String reg_stylist_name;//设计师姓名
	private String reg_stylist_mobile;//设计师电话
	private String quo_id;//报价单ID
	private String contractbegindate;//合同开工日期
	private String contractenddate;//合同竣工日期
	private String date_number;//工期
	private String bisiness_id;//企业ID
	
	private List<ContractDiscount> contractList;//合同优惠
	
	private ContractAmount contractAmount;//项目相关金额
	
	private Map<String,ContractPayment> cpMap;//客户交款情况
	
	public Map<String, ContractPayment> getCpMap() {
		return cpMap;
	}
	public void setCpMap(Map<String, ContractPayment> cpMap) {
		this.cpMap = cpMap;
	}
	public ContractAmount getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(ContractAmount contractAmount) {
		this.contractAmount = contractAmount;
	}
	public List<ContractDiscount> getContractList() {
		return contractList;
	}
	public void setContractList(List<ContractDiscount> contractList) {
		this.contractList = contractList;
	}
	public String getFormal_contract_id() {
		return formal_contract_id;
	}
	public void setFormal_contract_id(String formal_contract_id) {
		this.formal_contract_id = formal_contract_id;
	}
	public String getContract_num() {
		return contract_num;
	}
	public void setContract_num(String contract_num) {
		this.contract_num = contract_num;
	}
	public String getContract_type() {
		return contract_type;
	}
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}
	public String getContract_type_name() {
		return contract_type_name;
	}
	public void setContract_type_name(String contract_type_name) {
		this.contract_type_name = contract_type_name;
	}
	public String getFill_date() {
		return fill_date;
	}
	public void setFill_date(String fill_date) {
		this.fill_date = fill_date;
	}
	public String getContractsigned_date() {
		return contractsigned_date;
	}
	public void setContractsigned_date(String contractsigned_date) {
		this.contractsigned_date = contractsigned_date;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getReg_phone() {
		return reg_phone;
	}
	public void setReg_phone(String reg_phone) {
		this.reg_phone = reg_phone;
	}
	public String getReg_village_name() {
		return reg_village_name;
	}
	public void setReg_village_name(String reg_village_name) {
		this.reg_village_name = reg_village_name;
	}
	public String getReg_construct_address() {
		return reg_construct_address;
	}
	public void setReg_construct_address(String reg_construct_address) {
		this.reg_construct_address = reg_construct_address;
	}
	public String getReg_customer_mgr() {
		return reg_customer_mgr;
	}
	public void setReg_customer_mgr(String reg_customer_mgr) {
		this.reg_customer_mgr = reg_customer_mgr;
	}
	public String getReg_customer_mgr_name() {
		return reg_customer_mgr_name;
	}
	public void setReg_customer_mgr_name(String reg_customer_mgr_name) {
		this.reg_customer_mgr_name = reg_customer_mgr_name;
	}
	public String getReg_customer_mgr_mobile() {
		return reg_customer_mgr_mobile;
	}
	public void setReg_customer_mgr_mobile(String reg_customer_mgr_mobile) {
		this.reg_customer_mgr_mobile = reg_customer_mgr_mobile;
	}
	public String getReg_project_mgr() {
		return reg_project_mgr;
	}
	public void setReg_project_mgr(String reg_project_mgr) {
		this.reg_project_mgr = reg_project_mgr;
	}
	public String getReg_project_mgr_name() {
		return reg_project_mgr_name;
	}
	public void setReg_project_mgr_name(String reg_project_mgr_name) {
		this.reg_project_mgr_name = reg_project_mgr_name;
	}
	public String getReg_project_mgr_mobile() {
		return reg_project_mgr_mobile;
	}
	public void setReg_project_mgr_mobile(String reg_project_mgr_mobile) {
		this.reg_project_mgr_mobile = reg_project_mgr_mobile;
	}
	public String getReg_stylist() {
		return reg_stylist;
	}
	public void setReg_stylist(String reg_stylist) {
		this.reg_stylist = reg_stylist;
	}
	public String getReg_stylist_name() {
		return reg_stylist_name;
	}
	public void setReg_stylist_name(String reg_stylist_name) {
		this.reg_stylist_name = reg_stylist_name;
	}
	public String getReg_stylist_mobile() {
		return reg_stylist_mobile;
	}
	public void setReg_stylist_mobile(String reg_stylist_mobile) {
		this.reg_stylist_mobile = reg_stylist_mobile;
	}
	public String getQuo_id() {
		return quo_id;
	}
	public void setQuo_id(String quo_id) {
		this.quo_id = quo_id;
	}
	
	public String getContractbegindate() {
		return contractbegindate;
	}
	public void setContractbegindate(String contractbegindate) {
		this.contractbegindate = contractbegindate;
	}
	public String getContractenddate() {
		return contractenddate;
	}
	public void setContractenddate(String contractenddate) {
		this.contractenddate = contractenddate;
	}
	public String getDate_number() {
		return date_number;
	}
	public void setDate_number(String date_number) {
		this.date_number = date_number;
	}
	public String getBisiness_id() {
		return bisiness_id;
	}
	public void setBisiness_id(String bisiness_id) {
		this.bisiness_id = bisiness_id;
	}

}
