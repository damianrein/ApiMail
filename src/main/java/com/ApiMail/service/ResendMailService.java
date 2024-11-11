package com.ApiMail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

@Service
public class ResendMailService {
    
	@Value("${email.api_key}")
	private String key;

	public void sendMailWithResend(String destinatario) {
		Resend resend = new Resend(key);

        CreateEmailOptions params = CreateEmailOptions.builder()
        .from("onboarding@resend.dev")//springboot472@gmail.com
        .to(destinatario)
        .subject("it works!")
        .html("<strong>hello world</strong>")
        .build();
        
        {
 try {
    CreateEmailResponse data = resend.emails().send(params);
    System.out.println(data.getId());
} catch (ResendException e) {
    e.printStackTrace();
}

}
	}
}
