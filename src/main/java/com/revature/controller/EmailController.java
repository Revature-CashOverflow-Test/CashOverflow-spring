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

/**
 * controller layer for sending email to users
 * 
 * @author Nathaniel Blichfeldt
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class EmailController {

	EmailService emailService;
	
	@Autowired
	protected EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/**
	 * sends the email information to the service layer to be constructed and sent
	 * 
	 * @param data email information (reciepiant's email, email subject, email body)
	 */
	@PostMapping("/sendemail")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean sendEmail(@RequestBody EmailData data) {
		return emailService.send(data);
	}
	
}
