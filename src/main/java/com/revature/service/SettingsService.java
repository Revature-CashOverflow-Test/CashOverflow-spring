package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.SettingsRepo;

@Service
public class SettingsService {
	
	private SettingsRepo settingsRepo;
	
	@Autowired
	protected SettingsService(SettingsRepo settingsRepo) {
		this.settingsRepo = settingsRepo;
	}
	
	public int changePassword(String username, String password) {
		if(username == null || password == null) {
			return 0;
		}else {
			return settingsRepo.changePassword(username, password);
		}
		
	}
	
	/**
	 * 
	 * @param username the username of the user
	 * @param emailToggle the user's new emailToggle value
	 * @param emailValue the user's new emailValue value
	 * @return the failure or success of the change on the database
	 */
	public boolean changeEmailSettings(String username, boolean emailToggle, double emailValue) {
		return settingsRepo.changeEmailSettings(username, emailToggle, emailValue) == 1;
	}

}
