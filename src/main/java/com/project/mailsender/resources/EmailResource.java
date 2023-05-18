package com.project.mailsender.resources;

import com.project.mailsender.model.Email;
import com.project.mailsender.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EmailResource {

    @Autowired
    EmailService service;

    @PostMapping("/send-email-simple-template")
    public ResponseEntity<Email> sendSimpleEmail(@RequestBody Email email) throws MessagingException {
        service.sendSimpleEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

    @PostMapping("/send-email-attachment-template")
    public ResponseEntity<Email> sendAttachmentEmail(@RequestBody Email email) throws MessagingException {
        service.sendEmailwithAttachment(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

}
