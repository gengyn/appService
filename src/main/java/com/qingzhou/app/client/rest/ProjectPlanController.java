package com.qingzhou.app.client.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingzhou.app.client.domain.RestProjectPlan;
import com.qingzhou.app.client.service.ProjectPlanService;
import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service/projectplan")
public class ProjectPlanController extends BaseController{

	private ProjectPlanService projectPlanService;
	
	
	@RequestMapping(value = "/{customer_id}", method = RequestMethod.GET)
	public @ResponseBody
	RestProjectPlan getProjectPhanByIDJSON(@PathVariable String customer_id) {
		
		logger.info("根据客户ID获取工程进度信息，customer_id=" + customer_id);
		
		projectPlanService = this.getBean("projectPlanService");
		RestProjectPlan projectPlan = projectPlanService.getProjectPlan(customer_id);
		return projectPlan;
	}
	
	
	
}