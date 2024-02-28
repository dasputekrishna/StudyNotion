package com.project.study.controller;
	
import com.project.study.model.UserModel;
import com.project.study.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

	@RestController
	@RequestMapping("/api/users")
	public class UserController {

		
		
		    @Autowired
		    private UserRepository userRepository;

		    @GetMapping("/")
		    public List<UserModel> getAllUsers() {
		        return userRepository.findAll();
		    }
		    
		    
		    @PostMapping("/")
		    public UserModel createUser(@RequestBody UserModel user) {
		        return userRepository.save(user);
		    }

		    @GetMapping("/{id}")
		    public ResponseEntity<UserModel> getUserById(@PathVariable(value = "id") Long userId) {
		        UserModel user = userRepository.findById(userId)
		                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		        return ResponseEntity.ok().body(user);
		    }

		    @PutMapping("/{id}")
		    public ResponseEntity<UserModel> updateUser(@PathVariable(value = "id") Long userId,
		                                           @RequestBody UserModel userDetails) {
		        UserModel user = userRepository.findById(userId)
		                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		        user.setUsername(userDetails.getUsername());
		        user.setEmail(userDetails.getEmail());

		        final UserModel updatedUser = userRepository.save(user);
		        return ResponseEntity.ok(updatedUser);
		    }

		    @DeleteMapping("/{id}")
		    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) {
		        UserModel user = userRepository.findById(userId)
		                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		        userRepository.delete(user);
		        Map<String, Boolean> response = new HashMap()<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
		    }
		}
	
       
		   
		
		
