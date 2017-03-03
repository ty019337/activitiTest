package actest;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
public class FormTest {

	@Test
	public void test() throws Exception{
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		//部署流程
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		InputStream in = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\MyProcess.bar"));
//		repositoryService.createDeployment().addZipInputStream(new ZipInputStream(in)).deploy();
//		repositoryService.createDeploymentQuery().list();
		

		
		//启动流程 并查看流程
//		Map<String, Object> vas = new HashMap<String, Object>();
//		vas.put("acct_no", "1010001005012");
//		vas.put("money", 100.13);
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		runtimeService.startProcessInstanceByKey("myProcess", "PEM02001", vas);
//		List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().processDefinitionId("myProcess:5:65004").list();
//		for(ProcessInstance processInstance : processInstanceList){
//			System.out.println(processInstance.getId()+"|"+processInstance.getProcessDefinitionId());
//		}
		
		//查看用户列表
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned("user1").list();
		Task task = taskService.createTaskQuery().taskId("72506").singleResult();
		Map<String, Object> vas = taskService.getVariables("72506");
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		System.out.println(vas.get("acct_no"));
		System.out.println(processInstance.getBusinessKey());
		
	}
}
