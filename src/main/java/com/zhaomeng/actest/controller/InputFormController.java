package com.zhaomeng.actest.controller;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="form")
public class InputFormController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="inputform")
	public String input(){
		return "input";
	}
	
	@RequestMapping(value="checkform")
	public String check(){
		return "check";
	}
	
	@RequestMapping(value="inputsave")
	public String inputSave(HttpServletRequest request){
		String biz_no = request.getParameter("biz_no");
		String acct = request.getParameter("acct");
		System.out.println(biz_no);
		System.out.println(acct);
		taskService.complete(request.getParameter("taskId"));
		return "redirect:/task/task-list";
	}
}
