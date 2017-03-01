package actest;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class RestTest {

	@Test
	public void test(){
		//²¿ÊðÁ÷³Ì
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addClasspathResource("MyProcess.bpmn");
		deploymentBuilder.addClasspathResource("MyProcess.png");
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println(deployment.getId());
	}
}
