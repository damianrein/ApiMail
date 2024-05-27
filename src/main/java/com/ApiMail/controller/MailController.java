package com.ApiMail.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiMail.model.MailFile;
import com.ApiMail.model.MailSimple;
import com.ApiMail.service.MailService;

@RestController
@RequestMapping("/email")
public class MailController {

	private MailService mailService;
	
	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@PostMapping("/email")
	public ResponseEntity<?> requestEmail(@RequestBody MailSimple request){
		
		mailService.sendEmail(request.getToUser(), request.getSubject(), request.getMessage());
		
		Map<String, String> response = new HashMap<>();
		response.put("estado", "Enviado");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/emailFile")
	public ResponseEntity<?> requestEmailWithFile(@RequestBody MailFile request) throws IOException{
		
		String filename = request.getFile().getOriginalFilename();
		
		Path path = Paths.get("src/main/resources/files" + filename);
		
		Files.createDirectories(path.getParent());
		Files.copy(request.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		File file = path.toFile();
		
		mailService.sendEmail(request.getToUser(), request.getSubject(), request.getMessage(), file);
		
		Map<String, String> response = new HashMap<>();
		response.put("estado", "Enviado");
		response.put("archivo", filename);
		
		return ResponseEntity.ok(response);
	}
}
