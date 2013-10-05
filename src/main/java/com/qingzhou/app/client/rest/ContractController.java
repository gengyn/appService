package com.qingzhou.app.client.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.app.client.domain.ContractDiscount;
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
		contract = contractService.selectByCustomer(contract);
		contract.setContractList(contractService.listContractDiscount(contract));
		return contract;
	}
	
	
	
}