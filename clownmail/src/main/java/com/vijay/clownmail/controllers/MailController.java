package com.vijay.clownmail.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.clownmail.models.Mail;
import com.vijay.clownmail.services.MailService;

@RestController
@RequestMapping("/api/mails")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping("/send")
	public Mail sendMail(@RequestBody Mail mail) {
		return mailService.sendMail(mail);
	}
	
	@GetMapping("/inbox/{email}")
	public Optional<Mail> getInbox(@PathVariable String email){
		return mailService.getInbox(email);
	}
}
