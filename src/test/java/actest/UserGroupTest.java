package actest;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.zhaomeng.actest.utils.ActivitiUtils;
public class UserGroupTest {

	@Test
	public void test(){
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		//ActivitiUtils.getProcessEngine().getRuntimeService().startProcessInstanceByKey("myProcess");
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList = taskService.createTaskQuery().list();
		//Task task = taskList.get(0);
		//taskService.claim(task.getId(), "user2");
		
	}
}
