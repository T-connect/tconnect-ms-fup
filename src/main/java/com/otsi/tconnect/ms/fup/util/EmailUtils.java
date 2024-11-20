package com.otsi.tconnect.ms.fup.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailUtils {

    private static JavaMailSender javaMailSender;
    private static String sender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        EmailUtils.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    public void setSender(String sender) {
        EmailUtils.sender = sender;
    }

    public static void sendEmailToKadali(String subject, String text) {
        String recipientEmail = "narchintha@gmail.com"; // Replace with actual recipient
        sendEmail(recipientEmail, subject, text);
    }

    public static void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
        	 javaMailSender.send(message);
			log.info("Email Successfully Sent");
		} catch (Exception e) {
			log.error("Error occured while sending email");

		}
    }
}
