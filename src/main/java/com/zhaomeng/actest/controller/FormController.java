package com.zhaomeng.actest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

	@RequestMapping(value="form/input")
	public String input(){
		return "input";
	}
	
	@RequestMapping(value="form/check")
	public String check(){
		return "check";
	}
}
