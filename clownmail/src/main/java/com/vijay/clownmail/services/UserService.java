package com.vijay.clownmail.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vijay.clownmail.Security.JwtUtil;
import com.vijay.clownmail.models.Users;
import com.vijay.clownmail.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	    private JwtUtil jwtUtil;

	    public boolean existsByEmail(String email) {
	        return userRepository.findByEmail(email).isPresent();
	    }

	    public Users registerUser(Users user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	    public String login(Users user) {
	        Optional<Users> userOpt = userRepository.findByEmail(user.getEmail());

	        if (userOpt.isPresent()) {
	            Users users = userOpt.get();
	            if (passwordEncoder.matches(user.getPassword(), user.getPassword())) {
	                return jwtUtil.generateToken(users.getEmail());
	            }
	        }
	        return null;
	    }
}
