package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for social media connections
 *
 * @author Caleb
 */
@Entity
@Table(name = "user_social_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialMedia {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String username;
	String profileSub;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	UserAccount owner;

}
