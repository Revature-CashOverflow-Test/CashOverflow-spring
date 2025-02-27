package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Settings model that we will return to the client
 *
 * @author Micheal Bailey
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

	String username;
	String newPassword;
	String newFirstName;
	String newLastName;
	String newEmail;

	public SettingsDto(String username, String newPassword) {
		this.username = username;
		this.newPassword = newPassword;
	}

}
