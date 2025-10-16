package com.vijay.clownmail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.clownmail.models.User;
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
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	    try {
	        User user = userService.login(request.getEmail(), request.getPassword());
	        return ResponseEntity.ok(user);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	// DTO class
	public static class LoginRequest {
	    private String email;
	    private String password;

	    public LoginRequest() {}

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }
	}
	
	@GetMapping
	public List<User> getAll(){
		return userService.getAllUsers();
	}
}
