package com.project.mailsender.resources;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailResource {

    
   private final EmailService service;

    @PostMapping("/send-email")
    public ResponseEntity<EmailDTO> sendEmail(@RequestBody EmailDTO dto) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.sendTemplateEmail(dto));
    }

    @PostMapping("/send-template-email")
    public ResponseEntity<EmailDTO> sendTemplateEmail(@RequestBody EmailDTO dto) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.sendTemplateEmail(dto));
    }

    @PostMapping("/send-email-attachment-template")
    public ResponseEntity<EmailDTO> sendTemplateEmailwithAttachment(@RequestBody EmailDTO dto) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.sendTemplateEmailwithAttachment(dto));
    }

    @GetMapping("/emails")
    public ResponseEntity<List<EmailDTO>> getAllEmails() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmailDTO> getEmailById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/{recipientName}")
    public ResponseEntity<List<EmailDTO>> getByRecipientName(String recipientName) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByRecipientName(recipientName));
    }

}
