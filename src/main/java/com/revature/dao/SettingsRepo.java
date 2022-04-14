package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.UserAccount;

@Repository
public interface SettingsRepo extends JpaRepository<UserAccount, Integer> {
	
    @Transactional 
	@Modifying
	@Query("UPDATE UserAccount SET password = :password WHERE username = :username")
	public int changePassword(@Param("username") String username, @Param("password") String password);

}
