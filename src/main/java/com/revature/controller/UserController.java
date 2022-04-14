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

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class UserController {
	
	@Autowired
	private UserAccountService userAccServ;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/userinfo")
	@ResponseStatus(HttpStatus.OK)
	public UserAccountDto getUserInfo(Authentication auth) {
		return convertToDto(userAccServ.getUserFromUsername(auth.getName()));
	}
	
	protected UserAccountDto convertToDto(UserAccount user) {
		return mapper.map(user, UserAccountDto.class);
	}
}
