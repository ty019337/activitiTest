package com.zhaomeng.actest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@RequestMapping(value="list")
	public ModelAndView getTaskList(HttpSession session){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/task/list");
		String userNmae = (String)session.getAttribute("userNmae");
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userNmae).list();
		mv.addObject("taskList", taskList);
		return mv;
	}
	
	@RequestMapping(value="start")
	public String taskStart(@RequestParam("processDefinitionId")String processDefinitionId){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String biz_no = String.format("DDM01%s%f", sf.format(new Date()), Math.random());
		runtimeService.startProcessInstanceById(processDefinitionId, biz_no);
		return "redirect:/task/list";
	}
	
	@RequestMapping(value="form")
	public ModelAndView taskForm(@RequestParam("taskId")String taskId){
		ModelAndView mv = new ModelAndView();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();	
		mv.setViewName("redirect:/task/"+task.getFormKey());
		mv.addObject("taskId", taskId);
		mv.addObject("biz_no", processInstance.getBusinessKey());
		return mv;
	}
	
	@RequestMapping(value="delete")
	public String taskDelete(@RequestParam("taskId")String taskId){
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		runtimeService.deleteProcessInstance(processInstanceId, "多余了 就删了");
		return "redirect:/task/list";
	}
}
