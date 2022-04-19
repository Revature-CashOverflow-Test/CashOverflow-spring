package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.SettingsRepo;

@Service
public class SettingsService {

	@Autowired
	private SettingsRepo settingsRepo;

	public int changePassword(String username, String password) {

		if ((username == null) || (password == null)) {
			return 0;
		} else {
			return settingsRepo.changePassword(username, password);
		}
	}

	public int changeFirstName(String username, String firstName) {
		if ((username == null) || (firstName == null)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}
		return settingsRepo.changeFirstName(username, firstName);
	}

	public int changeLastName(String username, String lastName) {
		if ((username == null) || (lastName == null)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}
		return settingsRepo.changeLastName(username, lastName);
	}

	public int changeEmail(String username, String email) {
		if ((username == null) || (email == null)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}
		return settingsRepo.changeEmail(username, email);
	}

	public boolean changeEmailSettings(String username, boolean emailToggle, double emailValue) {
		if ((username == null) || (emailValue < 0)) {
			return false;
		}
		return settingsRepo.changeEmailSettings(username, emailToggle, emailValue) == 1;
	}

}
