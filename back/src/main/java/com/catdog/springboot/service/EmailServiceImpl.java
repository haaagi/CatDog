package com.catdog.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }
    // 일반 메일
    public void sendMail() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        message.setSubject("hi");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("daehi4u@gmail.com"));
        message.setText("hi");
        message.setSentDate(new Date());
        emailSender.send(message);

    }
    // 첨부파일 메일
//    public void sendMailAttachment() throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo("daehi4u@gmail.com");
//        helper.setSubject("첨부파일 테스트");
//        helper.setText("attachment");
//
//        FileSystemResource file = new FileSystemResource(new File("resources/static/img/test.png"));
//        helper.addAttachment("resources/static/img/test.png", file);
//        emailSender.send(message);
//    }
}