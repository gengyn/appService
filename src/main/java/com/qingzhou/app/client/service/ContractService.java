package com.qingzhou.app.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingzhou.app.client.dao.ContractDao;
import com.qingzhou.app.client.domain.ContractDiscount;
import com.qingzhou.app.client.domain.Contract;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.service.BaseService;

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
