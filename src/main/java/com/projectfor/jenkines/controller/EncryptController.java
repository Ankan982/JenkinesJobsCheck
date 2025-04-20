package com.projectfor.jenkines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectfor.jenkines.model.entity.UserRequest;
import com.projectfor.jenkines.utility.CryptoUtil;

@RestController
@RequestMapping("/encryptRequest")
public class EncryptController {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/getUserRequest")
    public ResponseEntity<String> encryptJson(@RequestBody UserRequest request) {
        try {
            // Convert to JSON string
            String jsonString = objectMapper.writeValueAsString(request);
           System.out.println("Incoming request: "+jsonString);
            // Encrypt it
            String encrypted = CryptoUtil.encrypt(jsonString);

            return ResponseEntity.ok(encrypted);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Encryption failed: " + e.getLocalizedMessage());
        }
    }
}
