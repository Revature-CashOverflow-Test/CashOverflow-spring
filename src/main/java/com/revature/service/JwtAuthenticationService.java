package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.revature.model.JwtResponse;
import com.revature.util.JwtUtil;

/**
 * Class to handle JWT Authentication
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
@Service
public class JwtAuthenticationService {

	private JwtUtil util;
	private JwtUserDetailsService serv;
	private AuthenticationManager manager;

	@Autowired
	public JwtAuthenticationService(JwtUtil util, JwtUserDetailsService serv, AuthenticationManager manager) {
		this.util = util;
		this.serv = serv;
		this.manager = manager;
	}

	/**
	 * Method to create an AutheticationToken (to be called when the user logs in)
	 * 
	 * @param authenticationRequest - Incoming request
	 * @return - Response to front-end
	 * @throws Exception
	 */
	public ResponseEntity<JwtResponse> createAuthenticationToken(String username, String password)
			throws DisabledException, BadCredentialsException {

		authenticate(username, password);

		final UserDetails userDetails = serv.loadUserByUsername(username);

		final String token = util.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Create the authentication itself
	 * 
	 * @param username - Username of user to authenticate
	 * @param password - password of user to authenticate
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
}
