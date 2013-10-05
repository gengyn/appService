package com.qingzhou.app.client.rest;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.RestProjectPlan;
import com.qingzhou.app.client.domain.RestProjectPhoto;
import com.qingzhou.app.client.service.ProjectPlanService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/projectplan")
public class ProjectPlanController extends BaseController{

	private ProjectPlanService projectPlanService;
	
	
	@RequestMapping(value = "/{usertoken}/{customer_id}", method = RequestMethod.GET)
	public @ResponseBody
	RestProjectPlan getProjectPhanByIDJSON(@PathVariable String usertoken,@PathVariable String customer_id) {
		
		logger.info("根据客户ID获取工程进度信息，customer_id=" + customer_id);
		
		projectPlanService = this.getBean("projectPlanService");
		RestProjectPlan projectPlan = projectPlanService.getProjectPlan(customer_id);
		return projectPlan;
	}
	
	@RequestMapping(value = "/photo/{usertoken}/{schedetail_id}/{sche_id}", method = RequestMethod.GET)
	public @ResponseBody
	List<RestProjectPhoto> getProjectPhotoByIDJSON(@PathVariable String usertoken,@PathVariable String schedetail_id,@PathVariable String sche_id) {
		
		logger.info("根据进度ID获取图片");
		
		projectPlanService = this.getBean("projectPlanService");
		List<RestProjectPhoto> projectPhotoList = projectPlanService.getPhoto(schedetail_id, sche_id);
		return projectPhotoList;
	}
	
	
	
}