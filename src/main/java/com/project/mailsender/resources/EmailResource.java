package com.project.mailsender.resources;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class EmailResource {

    private final EmailService service;

    public EmailResource(EmailService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public ModelAndView home( ){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        ModelAndView mv = new ModelAndView("about");
        return mv;
    }



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

}
