package com.qingzhou.app.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.ContractDao;
import com.qingzhou.app.client.domain.BaseInventory;
import com.qingzhou.app.client.domain.BudgetPrice;
import com.qingzhou.app.client.domain.Budgetupdown;
import com.qingzhou.app.client.domain.ContractAmount;
import com.qingzhou.app.client.domain.ContractDiscount;
import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.app.client.domain.ContractPayment;
import com.qingzhou.app.client.domain.MaterialInventory;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;
import com.qingzhou.core.util.NumberUtils;

@Service
public class ContractService  extends BaseService {

	@Autowired
	private ContractDao contractDao;	

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}
	
	public List<ContractDiscount> listContractDiscount(Contract contract) {
		List<ContractDiscount> list; 
		list = contractDao.listContractDiscount(contract);
		return list;
	}
	
	public Contract selectByCustomer(Contract contract)
	{
		return contractDao.selectByCustomer(contract);
	}
	
	public ContractAmount selectAmountByCustomer(Contract contract)
	{
		return contractDao.selectAmountByCustomer(contract);
	}
	
	/**
	 * 调用存储过程，查询客户交款情况
	 * @param contract
	 * @return
	 */
	public Map<String,ContractPayment> getContractPayment(Contract contract)
	{
		List<ContractPayment> resultList = null;
		Map<String,ContractPayment> cpMap = new HashMap<String,ContractPayment>();
		Map<String,Object> param = new HashMap<String,Object>();	
		param.put("customer_id", contract.getCustomer_id());
		param.put("bisiness_id", contract.getBisiness_id());
		param.put("result", resultList);
		
		contractDao.listContractPayment(param);
		resultList= (List<ContractPayment>) param.get("result");
		
		for(int i=0;i<resultList.size();i++)
		{
			ContractPayment cp = resultList.get(i);
			cpMap.put(cp.getGatItem().trim(), cp);
		}
		
		return cpMap;
	}
	
	/**
	 * 获取基础结算清单
	 * @param param
	 * @return
	 */
	public List<BaseInventory> getBaseInventory(Map<String,String> param)
	{
		List<BaseInventory> baseInventoryList = contractDao.listBaseInventory(param);
		Contract contract = new Contract();
		contract.setCustomer_id(param.get("customer_id"));
		ContractAmount contractAmount = contractDao.selectAmountByCustomer(contract);
		//基础项目优惠
		baseInventoryList.add(getBaseDiscount(contractAmount));
		//设计费、管理费
		baseInventoryList.addAll(getBudget(param,contractAmount));
		//总计
		baseInventoryList.add(0, getBaseTotal(baseInventoryList));
		
		return baseInventoryList;
	}
	
	/**
	 * 基础总计金额
	 * @return
	 */
	private BaseInventory getBaseTotal(List<BaseInventory> baseInventoryList)
	{
		BaseInventory basetotal = new BaseInventory();
		basetotal.setBasName("总计");
		basetotal.setFlag("2");
		double total = 0;
		for (int i=0;i<baseInventoryList.size();i++)
		{
			total = total + Double.parseDouble(baseInventoryList.get(i).getPracticalPrice());
		}
		basetotal.setPracticalPrice(Double.toString(total));
		return basetotal;
	}
	
	/**
	 * 基础项目优惠
	 * @param param
	 * @return
	 */
	private BaseInventory getBaseDiscount(ContractAmount contractAmount)
	{
		BaseInventory baseInventory = new BaseInventory();
		baseInventory.setBasName("基础项目优惠");
		baseInventory.setFlag("1");
		baseInventory.setChangePrice(NumberUtils.addition(
									contractAmount.getHandItemPrivilege() +","+
									contractAmount.getHandItemAfterPrivilege() + "," +
									contractAmount.getOtherPrivilege() + "," +
									contractAmount.getOtherAfterPrivilege()));
		baseInventory.setPracticalPrice("-"+baseInventory.getChangePrice());
		return baseInventory;
	}
	/**
	 * 设计费、管理费等
	 * @param param
	 * @return
	 */
	private List<BaseInventory> getBudget(Map<String,String> param,ContractAmount contractAmount)
	{
		List<BaseInventory> designList = new ArrayList<BaseInventory>();
		BudgetPrice budget = contractDao.getBudget(param);
		Budgetupdown budgetupdown = contractDao.getBudgetupdown(param);
		//设计费
		BaseInventory designDomain = new BaseInventory();
		designDomain.setBasName("设计费");
		designDomain.setBaseCount("0");
		designDomain.setBudgetCount("0");
		designDomain.setBasePrice("");
		designDomain.setBasUnit("");
		designDomain.setContracePrice(NumberUtils.numDefault(budget.getQuo_design_cost()));
		designDomain.setChangePrice(NumberUtils.numDefault(budgetupdown.getUd_design_cost()));
		designDomain.setPracticalPrice(NumberUtils.addition(designDomain.getContracePrice() + "," + designDomain.getChangePrice()));
		designList.add(designDomain);
		//设计费优惠
		BaseInventory designDiscountDomain = new BaseInventory();
		designDiscountDomain.setBasName("设计费优惠");
		designDiscountDomain.setBaseCount("0");
		designDiscountDomain.setBudgetCount("0");
		designDiscountDomain.setBasePrice("");
		designDiscountDomain.setBasUnit("");
		designDiscountDomain.setContracePrice("");
		designDiscountDomain.setChangePrice(NumberUtils.addition(
										contractAmount.getDesignPrivilege() +","+
										contractAmount.getDesignAfterPrivilege()));
		designDiscountDomain.setPracticalPrice("-"+designDiscountDomain.getChangePrice());
		designDiscountDomain.setFlag("1");
		designList.add(designDiscountDomain);
		//管理费
		BaseInventory manageDomain = new BaseInventory();
		manageDomain.setBasName("工程管理费");
		manageDomain.setBaseCount("0");
		manageDomain.setBudgetCount("0");
		manageDomain.setBasePrice("");
		manageDomain.setBasUnit("");
		manageDomain.setContracePrice(NumberUtils.numDefault(budget.getQuo_manage_cost()));
		manageDomain.setChangePrice(NumberUtils.numDefault(budgetupdown.getUdt_manage_cost()));
		manageDomain.setPracticalPrice(NumberUtils.addition(manageDomain.getContracePrice() + "," + manageDomain.getChangePrice()));
		designList.add(manageDomain);
		//材料运输费
		BaseInventory transportDomain = new BaseInventory();
		transportDomain.setBasName("材料运输费");
		transportDomain.setBaseCount("0");
		transportDomain.setBudgetCount("0");
		transportDomain.setBasePrice("");
		transportDomain.setBasUnit("");
		transportDomain.setContracePrice(NumberUtils.numDefault(budget.getQuo_transport_cost()));
		transportDomain.setChangePrice(NumberUtils.numDefault(budgetupdown.getUdt_transport_cost()));
		transportDomain.setPracticalPrice(NumberUtils.addition(transportDomain.getContracePrice() +","+ transportDomain.getChangePrice()));
		designList.add(transportDomain);
		//材料损耗费
		BaseInventory wastageDomain = new BaseInventory();
		wastageDomain.setBasName("材料损耗费");
		wastageDomain.setBaseCount("0");
		wastageDomain.setBudgetCount("0");
		wastageDomain.setBasePrice("");
		wastageDomain.setBasUnit("");
		wastageDomain.setContracePrice(NumberUtils.numDefault(budget.getQuo_wastage_cost()));
		wastageDomain.setChangePrice(NumberUtils.numDefault(budgetupdown.getUdt_wastage_cost()));
		wastageDomain.setPracticalPrice(NumberUtils.addition(wastageDomain.getContracePrice() +","+ wastageDomain.getChangePrice()));
		designList.add(wastageDomain);
		//垃圾清运费
		BaseInventory clenaDomain = new BaseInventory();
		clenaDomain.setBasName("垃圾清运费");
		clenaDomain.setBaseCount("0");
		clenaDomain.setBudgetCount("0");
		clenaDomain.setBasePrice("");
		clenaDomain.setBasUnit("");
		clenaDomain.setContracePrice(NumberUtils.numDefault(budget.getQuo_clena_cost()));
		clenaDomain.setChangePrice(NumberUtils.numDefault(budgetupdown.getUdt_clena_cost()));
		clenaDomain.setPracticalPrice(NumberUtils.addition(clenaDomain.getContracePrice() +","+ clenaDomain.getChangePrice()));
		designList.add(clenaDomain);
		
		//管理费优惠
		BaseInventory manageDiscountDomain = new BaseInventory();
		manageDiscountDomain.setBasName("管理费优惠");
		manageDiscountDomain.setBaseCount("0");
		manageDiscountDomain.setBudgetCount("0");
		manageDiscountDomain.setBasePrice("");
		manageDiscountDomain.setBasUnit("");
		manageDiscountDomain.setContracePrice("");
		manageDiscountDomain.setChangePrice(NumberUtils.addition(
										contractAmount.getManagePrivilege() +","+
										contractAmount.getManageAfterPrivilege()));
		manageDiscountDomain.setPracticalPrice("-"+manageDiscountDomain.getChangePrice());
		manageDiscountDomain.setFlag("1");
		designList.add(manageDiscountDomain);
		return designList;
	}
	
	/**
	 * 获取主材结算清单
	 * @param param
	 * @return
	 */
	public List<MaterialInventory> getMaterialinventory(Map<String,String> param)
	{
		List<MaterialInventory> materialInventoryList = contractDao.listMaterialInventory(param);
		//主材优惠
		materialInventoryList.add(getMaterialDiscount(param));
		//总计
		materialInventoryList.add(0, getMaterialTotal(materialInventoryList));
		
		return materialInventoryList;
	}
	
	/**
	 * 主材优惠
	 * @param param
	 * @return
	 */
	public MaterialInventory getMaterialDiscount(Map<String,String> param)
	{
		MaterialInventory materialInventory = new MaterialInventory();
		Contract contract = new Contract();
		contract.setCustomer_id(param.get("customer_id"));
		ContractAmount contractAmount = contractDao.selectAmountByCustomer(contract);
		
		materialInventory.setMaterialName("主材项目优惠");
		materialInventory.setFlag("1");
		materialInventory.setChangePrice(NumberUtils.addition(
				contractAmount.getMainItemPrivilege() + "," + 
				contractAmount.getMainItemAfterPrivilege()));
		
		materialInventory.setPracticalPrice("-"+materialInventory.getChangePrice());
		
		return materialInventory;
	}
	
	/**
	 * 主材总计金额
	 * @param materialInventoryList
	 * @return
	 */
	public MaterialInventory getMaterialTotal(List<MaterialInventory> materialInventoryList)
	{
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterialName("总计金额");
		materialInventory.setFlag("2");
		double total = 0;
		for (int i = 0;i<materialInventoryList.size();i++)
		{
			total = total + Double.parseDouble(materialInventoryList.get(i).getPracticalPrice());
		}
		materialInventory.setPracticalPrice(Double.toString(total));
		return materialInventory;
	}

	@Override
	public Integer count(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List select(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
