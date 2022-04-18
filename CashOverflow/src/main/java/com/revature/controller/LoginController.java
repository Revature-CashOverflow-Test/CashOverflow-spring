package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.LoginRequestDto;
import com.revature.model.JwtResponse;
import com.revature.model.UserAccount;
import com.revature.service.JwtAuthenticationService;
import com.revature.service.UserAccountService;

/**
 * This Class is use to handle login functionality
 * 
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class LoginController {

	private UserAccountService serv;
	private PasswordEncoder enc;
	private JwtAuthenticationService jwtServ;

	@Autowired
	public LoginController(UserAccountService serv, JwtAuthenticationService jwtServ, PasswordEncoder enc) {
		this.serv = serv;
		this.jwtServ = jwtServ;
		this.enc = enc;
	}

	/**
	 * Checks if the User name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 * 
	 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
	 */
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequestDto req) {
		if (req.getUsername() == null || req.getPassword() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}
		UserAccount user = serv.getUserFromUsername(req.getUsername());
		if (user == null || !enc.matches(req.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.METHOD_FAILURE);
		} else {
			return jwtServ.createAuthenticationToken(user.getUsername(), req.getPassword());
		}
	}
}