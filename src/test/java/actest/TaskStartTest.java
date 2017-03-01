package actest;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
public class TaskStartTest {

	@Test	@Deployment(resources="")
	public void test(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		//流程部署
//		String deploymentId = processEngine.getRepositoryService().createDeployment().addClasspathResource("bmpn/designs/01/MyProcess.bpmn").deploy().getId();
//		System.out.println(deploymentId);
		
		//流程启动
//		String processDefinitionId = "myProcess:3:45004";
//		FormService formService = processEngine.getFormService();
//		formService.getRenderedStartForm(processDefinitionId);
//		Map<String, String> variables = new HashMap<String, String>();
//		variables.put("biz_no", "DDM010102301203");
//		variables.put("acct_no", "200100002401");
//		formService.submitStartFormData(processDefinitionId, variables);
		
		//流程查询
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned("user1").list();
		for(Task task : taskList){
			System.out.println(task.getId());
		}
	}
	
}
