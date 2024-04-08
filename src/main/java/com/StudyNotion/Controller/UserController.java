package com.StudyNotion.Controller;

import java.security.Principal;
//import java.security.Principal;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.StudyNotion.entity.Notes;
import com.StudyNotion.entity.User;
import com.StudyNotion.repository.UserRepository;
import com.StudyNotion.service.NotesService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	
	
	@Autowired
	private NotesService notesService; 

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Model m,Principal p,@RequestParam(defaultValue = "0")Integer pageNo) {
		User user = getUser(p, m);
		Page<Notes> notes = notesService.getNotesByUser(user,pageNo);
		
		m.addAttribute("currentPage",pageNo);
		m.addAttribute("totalElements" ,notes.getTotalElements());
		m.addAttribute("totalPages" , notes.getTotalPages());
		m.addAttribute("notesList",notes.getContent());
		return "view_notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {
		Notes notes = notesService.getNotesById(id);
		m.addAttribute("n",notes);
		return "edit_notes";
	}  
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,HttpSession session, Principal p, Model m) {
	  
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
		session.setAttribute("msg", "Notes save success");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/user/addNotes";
		
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session,  Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
		session.setAttribute("msg", "Notes update success");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/user/viewNotes";
   
		}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		boolean f = notesService.deleteNotes(id);
		
		if (f){
			    session.setAttribute("msg", "Delete success");
			} else {
				session.setAttribute("msg", "something wrong on server");
			}
		
		return "redirect:/user/viewNotes";
		
	}  
	
}





