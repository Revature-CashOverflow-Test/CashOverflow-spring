package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

/**
 * This Class Implements the LoginService Class and add functionality to the
 * methods
 * 
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private UserAccountRepo repo;

	@Autowired
	public LoginServiceImpl(UserAccountRepo repo) {
		this.repo = repo;
	}

	/**
	 * Method to login to the application
	 * 
	 * @param username - username enter to log in
	 * @param password - password enter to log in
	 * @return - User object
	 */
	@Override
	public UserAccount login(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
}
