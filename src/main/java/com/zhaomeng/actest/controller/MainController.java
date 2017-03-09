package com.zhaomeng.actest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="")
public class MainController {
	
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="welcome")
	public String welcome(){
		return "welcome";
	}
	
	@RequestMapping(value="log")
	public String login(){
		return "login";
	}
}
