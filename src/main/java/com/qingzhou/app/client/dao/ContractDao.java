package com.qingzhou.app.client.dao;

import java.util.List;
import java.util.Map;

import com.qingzhou.app.client.domain.BaseInventory;
import com.qingzhou.app.client.domain.BudgetPrice;
import com.qingzhou.app.client.domain.Budgetupdown;
import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.app.client.domain.ContractAmount;
import com.qingzhou.app.client.domain.ContractDiscount;
import com.qingzhou.app.client.domain.ContractPayment;
import com.qingzhou.app.client.domain.MaterialInventory;
import com.qingzhou.core.dao.MyBatisDao;
  
@MyBatisDao
public interface ContractDao {

	/**
	 * 根据合同查询合同优惠信息
	 * @param contract
	 * @return
	 */
	public List<ContractDiscount> listContractDiscount(Contract contract);

	
	/**
	 * 根据合同ID，查询合同详情
	 * @param contract
	 * @return
	 */
	public Contract selectByCustomer(Contract contract); 
	
	/**
	 * 根据客户ID，查询客户金额相关
	 * @param contract
	 * @return
	 */
	public ContractAmount selectAmountByCustomer(Contract contract);
	
	/**
	 * 查询客户交款情况
	 * @param contract
	 * @return
	 */
	public void listContractPayment(Map<String,Object> param); 
	
	/**
	 * 查询基础明细清单
	 * @param param
	 */
	public List<BaseInventory> listBaseInventory(Map<String,String> param);
	
	/**
	 * 设计费等
	 * @param param
	 * @return
	 */
	public BudgetPrice getBudget(Map<String,String> param);
	
	/**
	 * 设计费等增减项
	 * @param param
	 * @return
	 */
	public Budgetupdown getBudgetupdown(Map<String,String> param);
	
	/**
	 * 查询主材结算清单
	 * @param param
	 * @return
	 */
	public List<MaterialInventory> listMaterialInventory (Map<String,String> param);

}
