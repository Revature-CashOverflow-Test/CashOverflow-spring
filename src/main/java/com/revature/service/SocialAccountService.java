package com.revature.service;

import java.util.List;

import com.revature.model.UserAccount;
import com.revature.model.UserSocialMedia;

public interface SocialAccountService {

	public UserSocialMedia createSocial(UserSocialMedia newAccount);

	public UserAccount getSoicalOwner(UserSocialMedia social);

	public List<UserSocialMedia> getSocialAccounts(int id);

	public UserSocialMedia getSocialAccount(String username);

}
