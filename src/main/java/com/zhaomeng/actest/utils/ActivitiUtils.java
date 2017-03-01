package com.zhaomeng.actest.utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class ActivitiUtils {

	private static ProcessEngine processEngine;
	public static ProcessEngine getProcessEngine(){
		if(processEngine == null){
			processEngine = ProcessEngines.getDefaultProcessEngine();
		}
		return processEngine;
	}
}
