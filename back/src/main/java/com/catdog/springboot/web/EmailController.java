package com.catdog.springboot.web;

import com.catdog.springboot.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public String home3(HttpServletRequest req) throws MessagingException {
        System.out.println("first");
        EmailServiceImpl es = new EmailServiceImpl();
        es.setJavaMailSender(javaMailSender);
        es.sendMail();
//        es.sendMailAttachment();
        return "index";
    }
}
