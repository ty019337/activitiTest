package com.zhaomeng.actest.controller;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private IdentityService identityService;
	
	@RequestMapping(value="login")
	public String login(@RequestParam("username")String userNmae, @RequestParam("passwd")String passwd, HttpSession session){
		boolean checkPassword= identityService.checkPassword(userNmae, passwd);
		if(checkPassword){
			session.setAttribute("userNmae", userNmae);
			return "redirect:/index";
		}
		else{
			return "redirect:/log?error=用户名密码不正确";
		}
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.removeAttribute("userName");
		return "redirect:/log";
	}
	

}