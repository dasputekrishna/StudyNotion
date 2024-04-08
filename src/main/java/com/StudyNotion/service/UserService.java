package com.StudyNotion.service;

import com.StudyNotion.entity.User;

public interface UserService {
	
	public User saveUser(User user);

	public boolean existEmailCheck(String email);

}

