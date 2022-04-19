package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revature.dto.EmailData;

/**
 * service layer for sending emails from a preset email
 * 
 * @author Nathaniel Blichfeldt
 *
 */
@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	@Autowired
	protected EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/**
	 * sends a simple email to a user's email
	 * 
	 * @param data email information (reciepiant's email, email subject, email body)
	 */
	public boolean send(EmailData data) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setTo(data.getUserEmail());
		simpleMessage.setSubject(data.getEmailSubject());
		simpleMessage.setText(data.getEmailBody());
		
		try {
			mailSender.send(simpleMessage);
		} catch (MailException e) {
			return false;
		}
		
		return true;
	}
}
