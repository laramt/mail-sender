package com.project.mailsender.services;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.model.Email;

import java.util.List;

public interface EmailService {

    Email sendEmail(EmailDTO dto);

    Email sendTemplateEmail(EmailDTO dto);

    Email sendTemplateEmailwithAttachment(EmailDTO dto);

    List<EmailDTO> findAll();

    EmailDTO findById(Long id);

}
