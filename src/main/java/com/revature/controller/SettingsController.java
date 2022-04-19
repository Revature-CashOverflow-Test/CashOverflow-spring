package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.EmailSettingsDto;
import com.revature.dto.SettingsDto;
import com.revature.service.SettingsService;

/**
 * This Class is used to handle the change password functionality
 * 
 * @author Micheal Bailey, Nathaniel Blichfeldt
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class SettingsController {
	
	private SettingsService settingsServ;
	private PasswordEncoder encoder;

	@Autowired
	public SettingsController(SettingsService settingsServ, PasswordEncoder encoder) {
		this.settingsServ = settingsServ;
		this.encoder = encoder;
	}
	
	@PutMapping("/changePassword")
	public boolean changePassword(@RequestBody SettingsDto dto) {
		
		boolean success = false;
		
		int value = settingsServ.changePassword(dto.getUsername(), encoder.encode(dto.getNewPassword()));
		
		if(value == 1) {
			success = true;
		}
		return success;
	}
	
	@PutMapping("/changeEmailSettings")
	@ResponseStatus(HttpStatus.OK)
	public boolean changeEmailSettings(Authentication auth, @RequestBody EmailSettingsDto dto) {
		return settingsServ.changeEmailSettings(auth.getName(), dto.isEmailToggle(), dto.getEmailValue());
	}
}
