package com.revature.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

/**
 * controller for openning up an endpoint to get user information
 * 
 * @author Nathaniel Blichfeldt
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class UserController {
	
	private UserAccountService userAccServ;
	private ModelMapper mapper;
	
	@Autowired
	protected UserController(UserAccountService userAccServ, ModelMapper mapper) {
		this.userAccServ = userAccServ;
		this.mapper = mapper;
	}
	
	/**
	 * Gets the user by username and returns non-confidential information about the user
	 * 
	 * @param auth jwt authentication token
	 * @return information on the user
	 */
	@GetMapping("/userinfo")
	@ResponseStatus(HttpStatus.OK)
	public UserAccountDto getUserInfo(Authentication auth) {
		return convertToDto(userAccServ.getUserFromUsername(auth.getName()));
	}
	
	/**
	 * removes sensitive information from the information to send back to the client
	 * 
	 * @param user the user found in the database
	 * @return non-confidential information of the user
	 */
	protected UserAccountDto convertToDto(UserAccount user) {
		return mapper.map(user, UserAccountDto.class);
	}
}
