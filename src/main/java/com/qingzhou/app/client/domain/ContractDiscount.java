package com.qingzhou.app.client.domain;

public class ContractDiscount {

	private String discount_item;//优惠项目
	private String discount_type;//优惠类型
	private String discount_type_name;//优惠类型名称
	private String discount_mode;//优惠方式
	private String discount_mode_name;//优惠方式名称
	private String discount_content;//优惠内容
	private String item_value;//项目标价
	private String item_cost;//项目成本
	
	public String getDiscount_item() {
		return discount_item;
	}
	public void setDiscount_item(String discount_item) {
		this.discount_item = discount_item;
	}
	public String getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	public String getDiscount_type_name() {
		return discount_type_name;
	}
	public void setDiscount_type_name(String discount_type_name) {
		this.discount_type_name = discount_type_name;
	}
	public String getDiscount_mode() {
		return discount_mode;
	}
	public void setDiscount_mode(String discount_mode) {
		this.discount_mode = discount_mode;
	}
	public String getDiscount_mode_name() {
		return discount_mode_name;
	}
	public void setDiscount_mode_name(String discount_mode_name) {
		this.discount_mode_name = discount_mode_name;
	}
	public String getDiscount_content() {
		return discount_content;
	}
	public void setDiscount_content(String discount_content) {
		this.discount_content = discount_content;
	}
	public String getItem_value() {
		return item_value;
	}
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	public String getItem_cost() {
		return item_cost;
	}
	public void setItem_cost(String item_cost) {
		this.item_cost = item_cost;
	}
}
