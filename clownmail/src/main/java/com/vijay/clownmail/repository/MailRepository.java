package com.vijay.clownmail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.clownmail.models.Mail;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long>{
	Optional<Mail> findByToEmail(String toEmail);
	Optional<Mail> findByFromEmail(String email);
	List<Mail> findByFromEmailAndToEmailContaining(String fromEmail, String toEmail);
}
