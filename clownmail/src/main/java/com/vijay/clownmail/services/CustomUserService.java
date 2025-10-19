package com.vijay.clownmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vijay.clownmail.models.Users;
import com.vijay.clownmail.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities("USER")
				.build();
	}

}
