package com.zhaomeng.actest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zhaomeng.actest.utils.ActivitiUtils;

@Controller
@RequestMapping(value="task")
public class TaskController {

	@RequestMapping(value="task-list")
	public ModelAndView getTaskList(HttpSession session){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("task-list");
		String userNmae = (String)session.getAttribute("userNmae");
		TaskService taskService = ActivitiUtils.getProcessEngine().getTaskService();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userNmae).list();
		mv.addObject("taskList", taskList);
		return mv;
	}
	
	@RequestMapping(value="task-start")
	public String taskStart(@RequestParam("processDefinitionId")String processDefinitionId){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String biz_no = String.format("DDM01%s%f", sf.format(new Date()), Math.random());
		System.out.println(biz_no);
		ActivitiUtils.getProcessEngine().getRuntimeService().startProcessInstanceById(processDefinitionId, biz_no);
		return "redirect:task-list";
	}
	
	@RequestMapping(value="task-form")
	public ModelAndView taskForm(@RequestParam("taskId")String taskId){
		ModelAndView mv = new ModelAndView();
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();	
		mv.setViewName("redirect:"+task.getFormKey());
		mv.addObject("taskId", taskId);
		mv.addObject("biz_no", processInstance.getBusinessKey());
		return mv;
	}
	
	@RequestMapping(value="task-delete")
	public String taskDelete(@RequestParam("taskId")String taskId){
		TaskService taskService = ActivitiUtils.getProcessEngine().getTaskService();
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		ActivitiUtils.getProcessEngine().getRuntimeService().deleteProcessInstance(processInstanceId, "多余了 就删了");
		return "redirect:task-list";
	}
}
