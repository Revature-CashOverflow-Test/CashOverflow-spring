package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

/**
 * Class to convert a UserAccount into UserDetails for use by JWT
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserAccountRepo userRepo;

	@Autowired
	public JwtUserDetailsService(UserAccountRepo repo) {
		this.userRepo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount temp = userRepo.findByUsername(username);
		if (temp != null) {
			return new User(username, temp.getPassword(), new ArrayList<>());

		} else
			throw new UsernameNotFoundException("User does not exist.");
	}

}
