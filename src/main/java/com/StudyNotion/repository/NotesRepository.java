package com.StudyNotion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.StudyNotion.entity.Notes;
import com.StudyNotion.entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer > {
	
	//public list<Notes> getNotesByUser(User user);
	
	public Page<Notes> findByUser(User user,Pageable pageable);
	
	

}
