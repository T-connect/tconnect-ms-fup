package com.otsi.tconnect.ms.fup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
	private String sender;

    public boolean sendCustomerNotification(String email,String fullName) {
		Context context = new Context();
		String templateName = "email/account-verification-success";
		String subject = "Welcome to FiberZ Network-FUP";
		context.setVariable("name", fullName);
		return sendEmail(templateName, email, subject, context);
	}
    
    private boolean sendEmail(String templateName, String recipientEmail, String subject, Context context) {
		try {
			String content = templateEngine.process(templateName, context);
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender, "FiberZ");
			helper.setTo(recipientEmail);
			helper.setSubject(subject);
			helper.setText(content, true);
			emailSender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
 
}
