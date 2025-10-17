package com.vijay.clownmail.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.clownmail.Utils.JwtUtil;
import com.vijay.clownmail.models.User;
import com.vijay.clownmail.payload.Login;
import com.vijay.clownmail.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	 public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login request) {
	    try {
	        User user = userService.login(request.getEmail(), request.getPassword());
	        String token = JwtUtil.generateToken(user.getEmail());
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Login successful");
	        response.put("token", token);
	        response.put("user", user);
	        
	        
	        return ResponseEntity.ok(response);
	        
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	
	@GetMapping
	public List<User> getAll(){
		return userService.getAllUsers();
	}
}
