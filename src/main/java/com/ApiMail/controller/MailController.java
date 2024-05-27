package com.ApiMail.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiMail.model.MailSimple;
import com.ApiMail.service.MailService;

@RestController
@RequestMapping("/email")
public class MailController {

	private MailService mailService;
	
	@PostMapping
	public ResponseEntity<?> requestEmail(@RequestBody MailSimple request){
		
		mailService.sendEmail(request.getToUser(), request.getSubject(), request.getMessage());
		
		Map<String, String> response = new HashMap<>();
		response.put("estado", "Enviado");
		
		return ResponseEntity.ok(response);
	}
}
