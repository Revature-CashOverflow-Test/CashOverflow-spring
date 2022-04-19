package com.revature.dto;

import com.revature.model.UserAccount;

import lombok.Data;

/**
 * Social Account that will be returned
 *
 * @author Caleb Kirschbaum
 */
@Data
public class SocialAccountDto {
	Integer id;
	String username;
	String profileSub;
	UserAccount owner;

}
