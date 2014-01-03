package com.qingzhou.app.client.domain;

public class MaterialInventory {

	private String materialSort;//类别
	private String materialBrand;//品牌
	private String materialName;//名称
	private String materialSpec;//描述
	private String materialModel;//型号
	private String materialUnit;//单位
	private String materialSalePrice;//单价
	private String budgetcount;//合同数量
	private String materialcount;//增减数量
	private String contractPrice;//合同金额
	private String changePrice;//增减金额
	private String practicalPrice;//实际金额
	private String flag;//优惠为1
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMaterialSort() {
		return materialSort;
	}
	public void setMaterialSort(String materialSort) {
		this.materialSort = materialSort;
	}
	public String getMaterialBrand() {
		return materialBrand;
	}
	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public String getMaterialSalePrice() {
		return materialSalePrice;
	}
	public void setMaterialSalePrice(String materialSalePrice) {
		this.materialSalePrice = materialSalePrice;
	}
	public String getBudgetcount() {
		return budgetcount;
	}
	public void setBudgetcount(String budgetcount) {
		this.budgetcount = budgetcount;
	}
	public String getMaterialcount() {
		return materialcount;
	}
	public void setMaterialcount(String materialcount) {
		this.materialcount = materialcount;
	}
	public String getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
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
