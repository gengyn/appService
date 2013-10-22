package com.qingzhou.app.client.view;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.qingzhou.core.domain.Page;
import com.qingzhou.core.exception.AppException;
import com.qingzhou.core.web.BaseAction;
import com.qingzhou.core.web.PromptMessage;
import com.qingzhou.app.client.domain.Aircraft;
import com.qingzhou.app.client.service.AircraftService;

/**
 * 航空器性能ACTION类
 * 
 * @author Zhou
 * 
 */
@Namespace("/test")
public class AircraftAction extends BaseAction {

	private static final Logger logger = LoggerFactory
			.getLogger(AircraftAction.class);
	private static final long serialVersionUID = 1L;
	private List<HashMap<String, Object>> aircraftList; // 跑道列表
	private AircraftService aircraftService;

	public String index() {
		return "index";
	}
	public String valid() {
		return "valid";
	}
	/**
	 * 进入航空器性能列表
	 * 
	 * @return
	 */
	public String showAircraftList() {
		aircraftService = this.getBean("aircraftService");
		try {
			Aircraft aircraft=(Aircraft) this.bindDataTableParm(Aircraft.class);
			//Page page = aircraftService.queryPage(aircraft);
			logger.info("  用户id =="+this.getLoginUser().getUserId());
			//renderText(page.toJsonString());
		} catch (Exception e) {
			logger.error("查询数据失败：[{}]", e);
//			AppException appException =AppException.getException("1001", e, "測試");
//			PromptMessage.createErrorPrompt("0003", appException, "測試").toJSonString();
		}
		return null;
	}
	
	public String create() {
		aircraftService = this.getBean("aircraftService");
		JSONObject json = new JSONObject();
		PromptMessage pMsg=null;
		try {
			Aircraft aircraft=(Aircraft) this.bindFormToObject(Aircraft.class);
			//TODO
//			aircraft.setJxnm(Math.random()+"");
			aircraftService.create(aircraft);
			pMsg=PromptMessage.createSuccessPrompt("0003", "航空器");
			System.out.println(pMsg.toJSonString());
		} catch (Exception e) {
			AppException appException =AppException.getException("1001", e, "测试");
			pMsg=PromptMessage.createErrorPrompt("0004", appException, "航空器");
//			throw appException;
		}
		this.renderText(pMsg.toJSonString());
		return null;
	}
	
	public String modify() {
		aircraftService = this.getBean("aircraftService");
		JSONObject json = new JSONObject();
		PromptMessage pMsg=null;
		try {
			Aircraft aircraft=(Aircraft) this.bindFormToObject(Aircraft.class);
//			aircraftService.modify(aircraft);
			pMsg=PromptMessage.createSuccessPrompt("0005", "航空器");
		} catch (Exception e) {
			AppException appException =AppException.getException("1001", e, "测试");
			pMsg=PromptMessage.createErrorPrompt("0006", appException, "航空器");
		}
		this.renderText(pMsg.toJSonString());
		return null;
	}
	
	public String remove() {
		aircraftService = this.getBean("aircraftService");
		JSONObject json = new JSONObject();
		PromptMessage pMsg=null;
		try {
			Aircraft aircraft=(Aircraft) this.bindFormToObject(Aircraft.class);
//			aircraftService.deleteByPKs(new String[]{aircraft.getJxnm()});
			pMsg=PromptMessage.createSuccessPrompt("0007", "航空器");
		} catch (Exception e) {
			AppException appException =AppException.getException("1001", e, "测试");
			pMsg=PromptMessage.createErrorPrompt("0008", appException, "航空器");
		}
		this.renderText(pMsg.toJSonString());
		return null;
	}
	/**
	 * 进入航空器性能界面(添加)
	 * 
	 * @return
	 */
	public String showAddAircraft() {
		return SUCCESS;
	}
}
