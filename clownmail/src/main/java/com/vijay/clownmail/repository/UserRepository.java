package com.vijay.clownmail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.clownmail.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByEmail(String email);
	boolean existsByEmail(String email);
}
