package com.projectfor.jenkines.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	 @Autowired
	 private Environment env;
	
	@GetMapping("/getApp")
	public String getAppDate()
	{	
		return "App is running !!. The APP name is: "+env.getProperty("spring.application.name");
	}
	
	@GetMapping("/getData")
	public String getCurrentData()
	{	
		
		return "Today's Date: "+new Date();
	}

}
