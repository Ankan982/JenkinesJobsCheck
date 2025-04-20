package com.projectfor.jenkines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectfor.jenkines.model.entity.UserRequest;

@RestController
@RequestMapping("/api/V1/window")
public class WindoController {

	@PostMapping("/postData")
    public ResponseEntity<String> handlePost(@RequestBody UserRequest userRequest) {

        return ResponseEntity.ok("Received request: " + userRequest.getName());
    }

}
