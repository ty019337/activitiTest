package com.zhaomeng.actest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zhaomeng.actest.utils.ActivitiUtils;

@Controller
public class TaskController {

	@RequestMapping(value="/task-list")
	public ModelAndView getTaskList(HttpSession session){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("task-list");
		String userNmae = (String)session.getAttribute("userNmae");
		TaskService taskService = ActivitiUtils.getProcessEngine().getTaskService();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userNmae).list();
		mv.addObject("taskList", taskList);
		return mv;
	}
	
	@RequestMapping(value="/task-start")
	public String taskStart(@RequestParam("processDefinitionId")String processDefinitionId){
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("acct_no", "1011000005101");
		ActivitiUtils.getProcessEngine().getFormService().submitStartFormData(processDefinitionId, "PEM0100000001", variables);
		return "redirect:task-list";
	}
	
	@RequestMapping(value="/task-form")
	public ModelAndView taskForm(@RequestParam("taskId")String taskId){
		ModelAndView mv = new ModelAndView();
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		Task task = ActivitiUtils.getProcessEngine().getTaskService().createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceBusinessKey("PEM0100000001").singleResult();
		Map<String, Object> processVariables = processInstance.getProcessVariables();
		Map<String, Object > variables = task.getProcessVariables();
		System.out.println(processVariables.get("biz_no").toString());
		System.out.println(variables.get("biz_no").toString());
		mv.setViewName("redirect:"+task.getFormKey());
		mv.addObject("variables", task.getProcessVariables());
		return mv;
	}
	
	@RequestMapping(value="/task-delete")
	public String taskDelete(@RequestParam("taskId")String taskId){
		ActivitiUtils.getProcessEngine().getTaskService().deleteTask(taskId);
		return "redirect:task-list";
	}
}
