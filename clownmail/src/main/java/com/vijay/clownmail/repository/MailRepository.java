package com.vijay.clownmail.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.clownmail.models.Mail;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long>{
	List<Mail> findByToEmail(String toEmail);
	List<Mail> findByFromEmail(String fromEmail);
	List<Mail> findByFromEmailAndToEmailContaining(String fromEmail, String toEmail);
}
