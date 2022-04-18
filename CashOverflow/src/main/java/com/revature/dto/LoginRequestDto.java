package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
public class LoginRequestDto {

	String username;
	String password;

}