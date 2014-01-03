package com.qingzhou.app.client.rest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.BaseInventory;
import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.app.client.domain.ContractDiscount;
import com.qingzhou.app.client.domain.ContractPayment;
import com.qingzhou.app.client.domain.MaterialInventory;
import com.qingzhou.app.client.service.ContractService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/contract")
public class ContractController extends BaseController{

	private ContractService contractService;
	
	/**
	 * 获取合同信息及优惠信息
	 * @param reg_name
	 * @param reg_phone
	 * @return
	 */
	@RequestMapping(value = "/{user_token}/{contract_id}", method = RequestMethod.GET)
	public @ResponseBody
	Contract getContractByIDJSON(@PathVariable String user_token,@PathVariable String contract_id) {
		
		logger.info("根据合同ID获取客户及合同信息，contract_id=" + contract_id);
		
		contractService = this.getBean("contractService");
		Contract contract = new Contract();
		contract.setFormal_contract_id(contract_id);
		//合同基本信息
		contract = contractService.selectByCustomer(contract);
		//优惠列表
		contract.setContractList(contractService.listContractDiscount(contract));
		//金额相关
		contract.setContractAmount(contractService.selectAmountByCustomer(contract));
		//客户交款情况
		contract.setCpMap(contractService.getContractPayment(contract));
		return contract;
	}
	/**
	 * 获取基础清单
	 * @param user_token
	 * @param quo_id
	 * @param bisiness_id
	 * @return
	 */
	@RequestMapping(value = "/baseinventory/{user_token}/{quo_id}/{bisiness_id}/{customer_id}", method = RequestMethod.GET)
	public @ResponseBody
	List<BaseInventory> getBaseInventoryByIDJSON(
			@PathVariable String user_token, @PathVariable String quo_id,
			@PathVariable String bisiness_id, @PathVariable String customer_id) {

		logger.info("基础结算清单，quo_id=" + quo_id);
		
		contractService = this.getBean("contractService");
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("quo_id", quo_id);
		param.put("bisiness_id", bisiness_id);
		param.put("customer_id", customer_id);
		
		return contractService.getBaseInventory(param);
	}
	
	/**
	 * 获取主材清单
	 * @param user_token
	 * @param quo_id
	 * @param bisiness_id
	 * @return
	 */
	@RequestMapping(value = "/materialinventory/{user_token}/{quo_id}/{bisiness_id}/{customer_id}", method = RequestMethod.GET)
	public @ResponseBody
	List<MaterialInventory> getMaterialInventoryByIDJSON(
			@PathVariable String user_token, @PathVariable String quo_id,
			@PathVariable String bisiness_id, @PathVariable String customer_id) {

		logger.info("主材结算清单，quo_id=" + quo_id);
		
		contractService = this.getBean("contractService");
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("quo_id", quo_id);
		param.put("bisiness_id", bisiness_id);
		param.put("customer_id", customer_id);
		
		return contractService.getMaterialinventory(param);
	}
	
	
	
}