package com.revature.dto;

import java.time.Instant;

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

}
