package com.StudyNotion.service;



import org.springframework.data.domain.Page;

import com.StudyNotion.entity.Notes;
import com.StudyNotion.entity.User;

public interface NotesService {
	
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);
	
	//public List<Notes> getNotesByUser(User user, pageNo);
	
	public Page<Notes> getNotesByUser(User user,int pageNo);
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);
	

}
