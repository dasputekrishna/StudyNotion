package com.project.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.study.model.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {
	
	

}
