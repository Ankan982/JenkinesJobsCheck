package com.projectfor.jenkines.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/getApp")
	public String getAppDate()
	{	
		return "App is running !!";
	}

}
