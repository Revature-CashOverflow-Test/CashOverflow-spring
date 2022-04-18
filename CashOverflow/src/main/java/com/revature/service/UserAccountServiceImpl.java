package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

/**
 * This be a service class for UserAccounts
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

	UserAccountRepo repo;

	@Autowired
	public UserAccountServiceImpl(UserAccountRepo repo) {
		this.repo = repo;
	}

	@Override
	public UserAccount getUserFromUsername(String username) {
		return repo.findByUsername(username);
	}

}
