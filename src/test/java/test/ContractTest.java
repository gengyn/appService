package test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.core.*; 
import com.qingzhou.app.client.service.ProjectPlanService;

@ContextConfiguration(locations = { "classpath:spring.xml"})

public class ContractTest extends TestBase{
	
	private ProjectPlanService projectPlanService;
	@BeforeClass
	protected void setUp() throws Exception {
		
		projectPlanService = (ProjectPlanService) applicationContext.getBean("projectPlanService");
	}

	@Test
	public void listProjectPlan()
	{
		projectPlanService.getProjectPlan("4028813040868084014086834f7d0001");
	}
}



