package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.EmailData;
import com.revature.service.EmailService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendemail")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendEmail(@RequestBody EmailData data) {
		System.out.println(data.toString());
		
		emailService.send(data);
	}
	
}
