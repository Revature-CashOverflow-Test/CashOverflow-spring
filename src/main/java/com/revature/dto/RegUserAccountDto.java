package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto object that creates a buffer between input data and the database
 *
 * @author Cameron, Amir, Chandra
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserAccountDto {

	String email;
	String username;
	String firstName;
	String lastName;
	String password;
	boolean auth0User;

	public RegUserAccountDto(String email, String username, String firstName, String lastName, String password) {
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		auth0User = false;

	}

}
