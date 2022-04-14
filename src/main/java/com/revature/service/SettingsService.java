package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.SettingsRepo;

@Service
public class SettingsService {

	@Autowired
	private SettingsRepo settingsRepo;

	public int changePassword(String username, String password) {
		return settingsRepo.changePassword(username, password);
	}

	public int changeFirstName(String username, String firstName) {
		return settingsRepo.changeFirstName(username, firstName);
	}

	public int changeLastName(String username, String lastName) {
		return settingsRepo.changeLastName(username, lastName);
	}

	public int changeEmail(String username, String Email) {
		return settingsRepo.changeEmail(username, Email);
	}
	
	public boolean changeEmailSettings(String username, boolean emailToggle, double emailValue) {
		return settingsRepo.changeEmailSettings(username, emailToggle, emailValue) == 1;
	}

}
