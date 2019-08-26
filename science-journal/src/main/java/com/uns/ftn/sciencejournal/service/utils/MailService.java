package com.uns.ftn.sciencejournal.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    public JavaMailSender javaMailSender;

    @Autowired
    public MailService( JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @Async
    public void sendEmail(String userEmail, String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

}