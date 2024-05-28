package ru.mirea.pract_24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${MAIL_FROM:no-reply@example.com}")
    private String mailFrom;
    @Value("${MAIL_TO:no-reply@example.com}")
    private String mailTo;

    @Async
    public void sendSimpleMessage(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    // Setters for testing
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }
}

