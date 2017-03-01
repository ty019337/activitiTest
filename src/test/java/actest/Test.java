package actest;

import org.activiti.engine.*;
import org.activiti.engine.runtime.*;
import org.activiti.engine.task.Task;

public class Test {
	public static void main(String[] args) {
		//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//		RepositoryService repositoryService = processEngine.getRepositoryService();
		//部署流程
//		repositoryService.createDeployment().addClasspathResource("ActivitiTest.bpmn20.xml").deploy();
		
		//查看流程定义
//		long num = repositoryService.createDeploymentQuery().count();
//		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionName("leave").singleResult();
		
		//
		//RuntimeService runtimeService = processEngine.getRuntimeService();
		//ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");

//		TaskService taskService  = processEngine.getTaskService();
//		Task task = taskService.createTaskQuery().taskCandidateGroup("").singleResult();
//		taskService.claim(task.getId(), "leaderUser");
		System.out.println("123");
//		System.out.println(processInstance.getId());
//		System.out.println(processInstance.getProcessDefinitionId());
//		System.out.println(processInstance.getProcessInstanceId());
	}
}
