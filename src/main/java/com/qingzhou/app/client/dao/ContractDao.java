package com.qingzhou.app.client.dao;

import java.util.List;

import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.app.client.domain.ContractDiscount;
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
	 * 根据客户姓名和客户电话，查询合同详情
	 * @param contract
	 * @return
	 */
	public Contract selectByCustomer(Contract contract); 
	
	
}
