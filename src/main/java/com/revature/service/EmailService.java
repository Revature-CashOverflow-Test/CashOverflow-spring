package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revature.dto.EmailData;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void send(EmailData data) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setTo(data.getUserEmail());
		simpleMessage.setSubject(data.getEmailSubject());
		simpleMessage.setText(data.getEmailBody());
		
		mailSender.send(simpleMessage);
	}
}
