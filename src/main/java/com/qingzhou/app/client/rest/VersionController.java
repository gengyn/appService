package com.qingzhou.app.client.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.Version;
import com.qingzhou.app.client.service.VersionService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/version")
public class VersionController extends BaseController{

	private VersionService versionService;
	
	/**
	 * 获取最新版本
	 */
	@RequestMapping(value = "/{os_type}/{ver_type}", method = RequestMethod.GET)
	public @ResponseBody
	Version getVersionJSON(@PathVariable String os_type,@PathVariable String ver_type) {
		
		logger.info("开始获取最新版本信息，os_type=" + os_type + ",ver_type=" + ver_type);
		Version version = new Version();
		version.setOs_type(os_type);
		version.setVer_type(ver_type);
		versionService = this.getBean("versionService");
		version = versionService.getLastVer(version);
		
		return version;

	}
	
	/**
	 * 新增，现为测试
	 * @param update_info
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody 
	Version postVersionJSON(@RequestBody Version version ) {
		
		logger.info("开始增加版本信息"+version.getUpdate_info());
	
		return version;

	}
	
	/**
	 * 更新，现为测试
	 * @param update_info
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	Version putVersionJSON(@RequestBody Version version) {
		
		logger.info("开始更新版本信息，dataJson=" + version.getUpdate_info() );
	
		return version;

	}
	/**
	 * 删除，现为测试
	 * @param update_info
	 * @return
	 */
	@RequestMapping(value = "/{update_info}", method = RequestMethod.DELETE)
	public @ResponseBody
	String delVersionJSON(@PathVariable String update_info) {
		
		logger.info("开始删除版本信息，update_info=" + update_info );
		Version version = new Version();
		version.setUpdate_info(update_info);
	
		return update_info;

	}

}