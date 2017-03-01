package com.zhaomeng.actest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
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
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmss");
		
		String dateNow = sf.format(new Date());
		variables.put("biz_no", "PEM01" + dateNow + "001");
		ActivitiUtils.getProcessEngine().getFormService().submitStartFormData(processDefinitionId, variables);
		return "redirect:task-list";
	}
	
	@RequestMapping(value="/task-form")
	public ModelAndView taskForm(@RequestParam("taskId")String taskId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("task-form");

		FormService formService = ActivitiUtils.getProcessEngine().getFormService();
		mv.addObject("renderForm", formService.getRenderedTaskForm(taskId));
		return mv;
	}
	
	@RequestMapping(value="/task-delete")
	public String taskDelete(@RequestParam("taskId")String taskId){
		ActivitiUtils.getProcessEngine().getTaskService().deleteTask(taskId);
		return "redirect:task-list";
	}
}
