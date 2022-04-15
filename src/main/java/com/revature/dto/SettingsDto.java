package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Settings model that we will return to the client
 *
 * @author Micheal Bailey
 */
@Data
@AllArgsConstructor
public class SettingsDto {

	String username;
	String newPassword;
	String newFirstName;
	String newLastName;
	String newEmail;

}
