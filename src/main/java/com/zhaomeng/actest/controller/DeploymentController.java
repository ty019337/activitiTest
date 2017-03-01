package com.zhaomeng.actest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhaomeng.actest.utils.ActivitiUtils;

@Controller
public class DeploymentController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="process-list")
	public ModelAndView processDefiList(){
		ModelAndView mv = new ModelAndView();
		RepositoryService repositoryService = ActivitiUtils.getProcessEngine().getRepositoryService();
		List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
		mv.addObject("processDefinitionList", processDefinitionList);
		mv.setViewName("process-list");
		return mv;
	}
	
	/**
	 * 流程部署
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="deploy")
	public String deploy(@RequestParam(value="file", required= true) MultipartFile file) throws IOException{
		String fileName = file.getOriginalFilename();
		String extensions = FilenameUtils.getExtension(fileName);
		InputStream inputStream = file.getInputStream();
		
		DeploymentBuilder deploymentBuilder = ActivitiUtils.getProcessEngine().getRepositoryService().createDeployment();
		if(extensions.equals("zip") || extensions.equals("bar")){
			deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
		}else{
			deploymentBuilder.addInputStream(fileName, inputStream);
		}
		deploymentBuilder.deploy();
		return "redirect:process-list";
	}
	
	@RequestMapping(value="getresource")
	public void getResource(@RequestParam("id")String id, @RequestParam("name")String name, HttpServletResponse response) throws Exception{
		RepositoryService repositoryService = ActivitiUtils.getProcessEngine().getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), name);
		byte[] b = new byte[1024];
		int len = -1;
		while((len = inputStream.read(b, 0, 1024)) != -1){
			response.getOutputStream().write(b, 0, 1024);
		}
	}
	
	@RequestMapping(value="delete-process")
	public String deleteProcess(@RequestParam("deploymentId")String id){
		RepositoryService repositoryService = ActivitiUtils.getProcessEngine().getRepositoryService();
		repositoryService.deleteDeployment(id);
		return "redirect:process-list";
	}
}
