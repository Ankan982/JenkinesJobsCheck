package com.projectfor.jenkines.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/getApp")
	public String getAppDate()
	{	
		return "App is running !!";
	}
	
	@GetMapping("/getData")
	public String getCurrentData()
	{	
		
		return "Today's Date: "+new Date();
	}

}
