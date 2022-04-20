package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.UserAccount;
import com.revature.model.UserSocialMedia;

@Repository
public interface SocialAccountRepo extends JpaRepository<UserSocialMedia, Integer> {

	public UserSocialMedia findAllById(Integer id);

	public UserSocialMedia findByUsername(String username);

	public UserSocialMedia findByProfileSub(String profileSub);

	public UserSocialMedia findByOwnerAndUsername(UserAccount owner, String username);
}
