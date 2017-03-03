package com.zhaomeng.actest.controller;

import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhaomeng.actest.utils.ActivitiUtils;

@Controller
@RequestMapping(value="inputForm")
public class InputController {

	@RequestMapping(value="save")
	public String input(HttpServletRequest request){
		String biz_no = request.getParameter("biz_no");
		String acct = request.getParameter("acct");
		System.out.println(biz_no);
		System.out.println(acct);
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		taskService.complete(request.getParameter("taskId"));
		return "redirect:/task-list";
	}
}
