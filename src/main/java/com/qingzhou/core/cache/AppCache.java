package com.qingzhou.core.cache;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.qingzhou.core.domain.ConfErrorCode;
import com.qingzhou.core.domain.ConfPromptMessage;
import com.qingzhou.core.service.ConfErrorCodeService;
import com.qingzhou.core.service.ConfPromptMessageService;
import com.qingzhou.core.util.PropertyUtils;
import com.qingzhou.core.util.SpringContextHolder;

@Repository
public class AppCache {
	/**
	 * 初始化ErrorCode配置
	 */
	public static final Map<String, String> errorInfoConfig = Maps
			.newConcurrentMap();
	//public static PropertyUtils errorConfig = new PropertyUtils("error.properties");

	/**
	 * 初始化prompt配置
	 */
	public static final Map<String, String> promptInfoConfig = Maps
			.newConcurrentMap();
	//public static PropertyUtils promptConfig = new PropertyUtils("prompt.properties");

	private static ConfErrorCodeService confErrorCodeService=SpringContextHolder.getBean(ConfErrorCodeService.class);

	private static ConfPromptMessageService confPromptMessageService=SpringContextHolder.getBean(ConfPromptMessageService.class);

	@PostConstruct
	public void init() {
		loadErrorConfig();
		loadPromptConfig() ;
	}
	
	public  void loadErrorConfig() {
		ConfErrorCode confErrorCode = new ConfErrorCode();
		List<ConfErrorCode> list = confErrorCodeService
				.listConfErrorCode(confErrorCode);
		for (ConfErrorCode code : list) {
			if (code == null)
				continue;
			errorInfoConfig.put(code.getId(), code.getMessage());
		}
	}
	
	public  void loadPromptConfig() {
		ConfPromptMessage confPromptMessage = new ConfPromptMessage();
		List<ConfPromptMessage> list = confPromptMessageService
				.listConfPromptMessage(confPromptMessage);
		for (ConfPromptMessage code : list) {
			if (code == null)
				continue;
			promptInfoConfig.put(code.getId(), code.getText());
		}
	}
}
