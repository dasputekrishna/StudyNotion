package com.project.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.study.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	

}
