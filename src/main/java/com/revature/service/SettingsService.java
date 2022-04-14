package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.SettingsRepo;

@Service
public class SettingsService {
	
	@Autowired
	private SettingsRepo settingsRepo;
	
	public int changePassword(String username, String password) {
		if(username == null || password == null) {
			return 0;
		}else {
			return settingsRepo.changePassword(username, password);
		}
		
	}

}
