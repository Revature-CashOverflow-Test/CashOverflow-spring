package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto object that creates a buffer between input data and the database
 * 
 * @author Cameron, Amir, Chandra
 */
@Data
@AllArgsConstructor
public class RegUserAccountDto {

	String email;
	String username;
	String firstName;
	String lastName;
	String password;

}
