package actest;

import java.util.List;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import com.zhaomeng.actest.utils.ActivitiUtils;


public class DeploymentList {
	
	/**
	 * 展示已部署流程
	 */
	@Test
	public void test() {
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		List<Deployment> deploymentList = processEngine.getRepositoryService().createDeploymentQuery().list();
		for(Deployment d : deploymentList){
			System.out.printf("%s%s", d.getId(), d.getName());
			System.out.println();
		}
	}
}
