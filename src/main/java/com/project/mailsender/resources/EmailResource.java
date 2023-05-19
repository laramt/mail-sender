package com.project.mailsender.resources;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.model.Email;
import com.project.mailsender.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@AllArgsConstructor
public class EmailResource {

    private final EmailService service;

    @PostMapping("/send-email")
    public ResponseEntity<EmailDTO> sendEmail(@RequestBody EmailDTO dto) throws MessagingException {
        service.sendTemplateEmail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/send-template-email")
    public ResponseEntity<EmailDTO> sendTemplateEmail(@RequestBody EmailDTO dto) throws MessagingException {
        service.sendTemplateEmail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/send-email-attachment-template")
    public ResponseEntity<EmailDTO> sendTemplateEmailwithAttachment(@RequestBody EmailDTO dto) throws MessagingException {
        service.sendTemplateEmailwithAttachment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<EmailDTO>> getAllEmails(@PageableDefault Pageable pageable){
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
