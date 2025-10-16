package com.vijay.clownmail.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.clownmail.models.Mail;
import com.vijay.clownmail.repository.MailRepository;

@Service
public class MailService {
	
	@Autowired
	private MailRepository mailRepository;
	
	
	public Mail sendMail(Mail mail) {
		return mailRepository.save(mail);
	}
	
	public Optional<Mail> getInbox(String email){
		return mailRepository.findByToEmail(email);
	}
}
