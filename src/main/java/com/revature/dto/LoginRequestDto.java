package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model for user accounts to send to the client
 *
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

	String username;
	String password;
	boolean auth0User;

	public LoginRequestDto(String username, String password) {
		this.username = username;
		this.password = password;
		auth0User = false;
	}

}