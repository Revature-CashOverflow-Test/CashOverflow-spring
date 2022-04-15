package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.revature.dto.EmailData;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
	@Mock
	private JavaMailSender mailSender;
	
	private EmailService emailService;
	
	@BeforeEach
	void setUpBeforeClass() {
		emailService = new EmailService(mailSender);
	}
	
	@Test
	void testSendEmailSuccess() {
		EmailData data = new EmailData("proto@gmail.com", "Car's Extended Warranty", "We've been trying to reach you concerning your vehicle's extended warranty. You should've received a notice in the mail about your car's extended warranty eligibility. Since we've not gotten a response, we're giving you a final courtesy call before we close out your file. Press 2 to be removed and placed on our do-not-call list. To speak to someone about possibly extending or reinstating your vehicle's warranty, press 1 to speak with a warranty specialist.");
		
		doNothing().when(mailSender).send(isA(SimpleMailMessage.class));
		
		assertTrue(emailService.send(data));
	}
	
	@Test
	void testSendEmailFail() {
		EmailData data = new EmailData("proto@gmail.com", "Car's Extended Warranty", "We've been trying to reach you concerning your vehicle's extended warranty. You should've received a notice in the mail about your car's extended warranty eligibility. Since we've not gotten a response, we're giving you a final courtesy call before we close out your file. Press 2 to be removed and placed on our do-not-call list. To speak to someone about possibly extending or reinstating your vehicle's warranty, press 1 to speak with a warranty specialist.");
		
		doThrow(MailSendException.class).when(mailSender).send(isA(SimpleMailMessage.class));
		
		assertFalse(emailService.send(data));
	}
}
