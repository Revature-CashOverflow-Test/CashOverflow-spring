package com.revature.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

@Service
public class RegisterServiceImpl implements RegisterService {

	UserAccountRepo userAccountRepo;

	@Autowired
	public RegisterServiceImpl(UserAccountRepo userAccountRepo) {
		this.userAccountRepo = userAccountRepo;
	}

	/**
	 * Uses the Java Instant class to add the creation date to the new UserAccount
	 * object.
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@Override
	public UserAccount insertUserAccount(UserAccount user) {
		user.setCreationDate(Instant.now());
		return userAccountRepo.save(user);
	}

}
