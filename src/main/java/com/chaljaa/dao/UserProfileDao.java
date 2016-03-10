package com.chaljaa.dao;

import java.util.List;

import com.chaljaa.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
