package com.vijay.clownmail.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.clownmail.Utils.JwtUtil;
import com.vijay.clownmail.models.Mail;
import com.vijay.clownmail.services.MailService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/mails")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping("/send")
	public ResponseEntity<?> sendMail(@RequestHeader("Authorization") String authHeader, @RequestBody Mail mail) {
		String token = authHeader.replace("Bearer ", "");
		String senderEmail = JwtUtil.validateToken(token);
		
		if(senderEmail == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid or expired token");
		}
		
		mail.setFromEmail(senderEmail);
		
		mailService.sendMail(mail);
		return ResponseEntity.ok("Mail sent successfully from " + senderEmail);
	}
	
	@GetMapping("/inbox")
	public ResponseEntity<?> viewInbox(HttpServletRequest request){
		

		String userEmail = (String) request.getAttribute("userEmail");
		
		if(userEmail == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid or expired token");
		}
		Optional<Mail> inbox = mailService.getInbox(userEmail);
		return ResponseEntity.ok(inbox);
	}
}
