package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dto.EmailData;
import com.revature.service.EmailService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmailControllerTest {
	@Mock
	private EmailService emailService;
	
	private EmailController emailController;
	
	@BeforeEach
	void setUpBeforeClass() {
		emailController = new EmailController(emailService);
	}
	
	@Test
	void testSendEmail() {
		EmailData data = new EmailData("proto@gmail.com", "Car's Extended Warranty", "We've been trying to reach you concerning your vehicle's extended warranty. You should've received a notice in the mail about your car's extended warranty eligibility. Since we've not gotten a response, we're giving you a final courtesy call before we close out your file. Press 2 to be removed and placed on our do-not-call list. To speak to someone about possibly extending or reinstating your vehicle's warranty, press 1 to speak with a warranty specialist.");
		
		when(emailService.send(data)).thenReturn(true);
		
		assertTrue(emailController.sendEmail(data));
	}
}