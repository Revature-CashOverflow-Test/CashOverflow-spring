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
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.EmailSettingsDto;
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
	public SettingsController(SettingsService settingsServ, PasswordEncoder encoder) {
		this.settingsServ = settingsServ;
		this.encoder = encoder;
	}



	@PutMapping("/changePassword")
	public boolean changePassword(@RequestBody SettingsDto dto) {
		boolean success = false;
		if (dto.getNewPassword() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "New password can't be null");
		}

		int value = settingsServ.changePassword(dto.getUsername(), encoder.encode(dto.getNewPassword()));

		if (value == 1) {
			success = true;
		}
		return success;
	}


	@PutMapping("/changeFirstName")
	public boolean changeFirstName(@RequestBody SettingsDto dto) {
		int value = settingsServ.changeFirstName(dto.getUsername(), dto.getNewFirstName());
		return (value >= 1);
	}

	@PutMapping("/changeLastName")
	public boolean changeLastName(@RequestBody SettingsDto dto) {
		int value = settingsServ.changeLastName(dto.getUsername(), dto.getNewLastName());
		return (value >= 1);
	}

	@PutMapping("/changeEmail")
	public boolean changeEmail(@RequestBody SettingsDto dto) {
		int value = settingsServ.changeEmail(dto.getUsername(), dto.getNewEmail());
		return (value >= 1);
	}

	@PutMapping("/changeEmailSettings")
	@ResponseStatus(HttpStatus.OK)
	public boolean changeEmailSettings(Authentication auth, @RequestBody EmailSettingsDto dto) {
		return settingsServ.changeEmailSettings(auth.getName(), dto.isEmailToggle(), dto.getEmailValue());
	}

}
