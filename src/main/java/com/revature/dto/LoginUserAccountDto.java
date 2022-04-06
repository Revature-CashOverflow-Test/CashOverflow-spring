package com.revature.dto;

import java.time.Instant;

import lombok.Data;

/**
 * 
 * Account info safe to be sent to the front end.
 * 
 * @author Delane Chen, Liliya Sherstobitova, Emmanuel Sosa
 *
 */
@Data
public class LoginUserAccountDto {

	Integer id;
	String username;
	String firstName;
	String lastName;
	Instant creationDate;

}