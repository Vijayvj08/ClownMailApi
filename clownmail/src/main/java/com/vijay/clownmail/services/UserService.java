package com.vijay.clownmail.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.clownmail.models.User;
import com.vijay.clownmail.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		
		if(existingUser.isPresent()) {
			throw new RuntimeException("Email already exists: "+ user.getEmail());
		}
		return userRepository.save(user);
	}
	
	public User login(String email, String password) {
	    Optional<User> userOpt = userRepository.findByEmail(email);
	    if (!userOpt.isPresent()) {
	        throw new RuntimeException("Email not registered");
	    }

	    User user = userOpt.get();
	    if (!user.getPassword().equals(password)) {
	        throw new RuntimeException("Incorrect password");
	    }

	    return user;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
