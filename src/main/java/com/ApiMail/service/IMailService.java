package com.ApiMail.service;

import java.io.File;

import jakarta.mail.MessagingException;

public interface IMailService {

	 void sendEmail(String[] toUser, String subject, String message) throws MessagingException;
	
	 void sendEmail(String[] toUser, String subject, String message, File file);
	 
	 
}
