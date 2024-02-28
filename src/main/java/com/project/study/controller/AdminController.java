package com.project.study.controller;



import com.project.study.model.AdminModel;
import com.project.study.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//AdminController.java

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	   

	    @Autowired
	    private AdminRepository adminRepository;

	    @GetMapping("/")
	    public List<AdminModel> getAllAdmins() {
	        return adminRepository.findAll();
	    }

	    // Implement other CRUD operations
	    
	    
	    @PostMapping("/")
	    public AdminModel createAdmin(@RequestBody AdminModel admin) {
	        return adminRepository.save(admin);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<AdminModel> getAdminById(@PathVariable(value = "id") Long adminId) {
	        AdminModel admin = adminRepository.findById(adminId)
	                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));
	        return ResponseEntity.ok().body(admin);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<AdminModel> updateAdmin(@PathVariable(value = "id") Long adminId,
	                                           @RequestBody Admin adminDetails) {
	        AdminModel admin = adminRepository.findById(adminId)
	                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));

	        admin.setUsername(adminDetails.getUsername());
	        admin.setEmail(adminDetails.getEmail());

	        final AdminModel updatedAdmin = adminRepository.save(admin);
	        return ResponseEntity.ok(updatedAdmin);
	    }

	    @DeleteMapping("/{id}")
	    public Map<String, Boolean> deleteAdmin(@PathVariable(value = "id") Long adminId) {
	        AdminModel admin = adminRepository.findById(adminId)
	                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));

	        adminRepository.delete(admin);
	        Map<String, Boolean> response = new HashMap()<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
	   
	
	
	
	
	
	
	
	
	
	
	


