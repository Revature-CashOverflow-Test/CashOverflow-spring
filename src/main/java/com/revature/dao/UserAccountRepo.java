package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.UserAccount;

/**
 * Repo for interacting with user accounts in the database.
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	/***
	 * Method to login to the application
	 * 
	 * @param username - username enter to log in
	 * @param password - password enter to log in
	 * @return - User object
	 */
	public UserAccount findByUsernameAndPassword(String username, String password);

	/**
	 * Find a user by their username only
	 * 
	 * @param username - username to find
	 * @return user found with given username
	 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
	 */
	UserAccount findByUsername(String username);
}
