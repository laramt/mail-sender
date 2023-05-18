package com.project.mailsender.resources;

import com.project.mailsender.model.Email;
import com.project.mailsender.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    @GetMapping("/emails")
    public ResponseEntity<Page<Email>> getAllEmails(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/email/{id}")
    public ResponseEntity<Object> getEmailById(@PathVariable(name = "id") Long id){
        Optional<Email> emailOptional = service.findById(id);
        if(!emailOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emailOptional.get());

    }

}
