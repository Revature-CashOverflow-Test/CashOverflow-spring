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
import com.revature.model.UserSocialMedia;
import com.revature.service.JwtAuthenticationService;
import com.revature.service.SocialAccountService;
import com.revature.service.UserAccountService;

/**
 * This Class is use to handle login functionality
 *
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net", "https://44.200.39.202" })
public class LoginController {

	private UserAccountService serv;
	private SocialAccountService saserv;
	private PasswordEncoder enc;
	private JwtAuthenticationService jwtServ;

	@Autowired
	public LoginController(UserAccountService serv, JwtAuthenticationService jwtServ, PasswordEncoder enc,
			SocialAccountService saserv) {
		this.serv = serv;
		this.jwtServ = jwtServ;
		this.enc = enc;
		this.saserv = saserv;
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
		// If a user is a Auth0 user, we don't want to check the password
		// So we go a different route instead of complexifying the rest of the
		// login info
		if (req.isAuth0User()) {
			return auth0Login(req);
		}
		if ((req.getUsername() == null) || (req.getPassword() == null)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}
		UserAccount user = serv.getUserFromUsername(req.getUsername());
		// Don't let an authuser sign in the normal way
		if ((user != null) && user.isAuthAccount()) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"Auth0 User can't sign in using the normal Login");
		}
		if ((user == null) || !enc.matches(req.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.METHOD_FAILURE);
		} else {
			return jwtServ.createAuthenticationToken(user.getUsername(), req.getPassword());
		}
	}

	private ResponseEntity<JwtResponse> auth0Login(@RequestBody LoginRequestDto req) {


		// Only checks the username since that is the only method that matters
		if ((req.getUsername() == null)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "missing Credential");
		}

		// Finds the main owner of the social account
		req = getSocialOwner(req);
		UserAccount user = serv.getUserFromUsername(req.getUsername());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User is not found");
		} else {
			// Password has to exists, so set it to the same as what is put in the
			// Register controller for auth users

			return jwtServ.createAuthenticationToken(user.getUsername(), "auth0User");
		}
	}

	// Recursive function to find the original owner.
	// Shouldn't be more than a few recursions
	private LoginRequestDto getSocialOwner(LoginRequestDto req) {
		UserSocialMedia social;
		try {
			String username = req.getUsername();
			social = saserv.getSocialAccount(username);
			// If the social is null, that means there are no owners with that user name
			if (social == null) {
				return req;
			}
			// If there is a owner of this user name, we need to repeat
			req.setUsername(social.getOwner().getUsername());
			return getSocialOwner(req);
		} catch (Exception e) {
			// This should be the same as the if statement
			// We should be able to get rid of either the try/catch
			// Or the if social==null
			return req;
		}
	}
}