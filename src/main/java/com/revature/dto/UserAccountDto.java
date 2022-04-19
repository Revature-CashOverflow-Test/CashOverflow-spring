package com.revature.dto;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.model.UserAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for user accounts to send to the client
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Data
@NoArgsConstructor
public class UserAccountDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String email;
	String username;
	String firstName;
	String lastName;
	Instant creationDate;
	Boolean emailToggle;
	Double emailValue;

	public UserAccountDto(UserAccount user) {
		id = user.getId();
		username = user.getUsername();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		creationDate = user.getCreationDate();
		emailToggle = user.getEmailToggle();
		emailValue = user.getEmailValue();
	}
}