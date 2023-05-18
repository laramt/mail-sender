package com.project.mailsender.resources;

import com.project.mailsender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EmailResource {

    @Autowired
    EmailService service;


}
