package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SettingsDto;
import com.revature.service.SettingsService;

/**
 * This Class is used to handle the change password functionality
 * 
 * @author Micheal Bailey
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class SettingsController {
	
	private SettingsService settingsServ;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	public SettingsController(SettingsService settingsServ) {
		this.settingsServ = settingsServ;
	}
	
	
	@PutMapping("/changePassword")
	public boolean  changePassword(@RequestBody SettingsDto dto) {
		
		boolean success = false;
		
		int value = settingsServ.changePassword(dto.getUsername(), encoder.encode(dto.getNewPassword()));
		
		if(value == 1) {
			success = true;
		}
		return success;
	}

}
