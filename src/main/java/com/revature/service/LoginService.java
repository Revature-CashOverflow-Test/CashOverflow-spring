package com.revature.service;

import com.revature.model.UserAccount;

/**
 * This Class takes form data and checks the database for existing credentials
 * 
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 */
public interface LoginService {

	/**
	 * Method to login to the application
	 * 
	 * @param username - username enter to log in
	 * @param password - password enter to log in
	 * @return - User object
	 */
	public UserAccount login(String username, String password);

}
