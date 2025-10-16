package com.vijay.clownmail.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mails")
public class Mail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fromEmail;
	private String toEmail;
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String body;
	
	private LocalDateTime sentAt = LocalDateTime.now();

	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mail(Long id, String fromEmail, String toEmail, String subject, String body, LocalDateTime sentAt) {
		super();
		this.id = id;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.sentAt = sentAt;
	}

	public Mail(String fromEmail, String toEmail, String subject, String body, LocalDateTime sentAt) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.sentAt = sentAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	
	
}
