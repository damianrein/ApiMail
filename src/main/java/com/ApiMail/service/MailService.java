package com.ApiMail.service;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.Pop3MailReceiver;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class MailService implements IMailService{

	@Value("$email.sender")
	private String emailUser;
	
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}
//
//
//-------------Envia Mail sin archivos
	@Override
	public void sendEmail(String[] toUser, String subject, String message) {
		
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setFrom(emailUser);
		simpleMail.setTo(toUser);
		simpleMail.setSubject(subject);
		simpleMail.setText(message);
		
		mailSender.send(simpleMail);
	}
//-------------Envia Mail con archivos
	@Override
	public void sendEmail(String[] toUser, String subject, String message, File file) {
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage,true,StandardCharsets.UTF_8.name());
			
			helper.setFrom(emailUser);
			helper.setTo(toUser);
			helper.setSubject(subject);
			helper.setText(message);
			helper.addAttachment(file.getName(), file);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
//------------------------------------------------------------------------------------------
//-------------PARTE QUE RECIBE LOS EMAIL---------------------------------------------------
//------------------------------------------------------------------------------------------

	public void receiveEmail() {
		
		MailReceiver receiver = new Pop3MailReceiver("");
		
	}
}
