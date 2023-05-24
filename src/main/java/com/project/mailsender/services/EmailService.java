package com.project.mailsender.services;

import com.project.mailsender.dtos.EmailDTO;

import java.util.List;

public interface EmailService {

    EmailDTO sendEmail(EmailDTO dto);

    EmailDTO sendTemplateEmail(EmailDTO dto);

    EmailDTO sendTemplateEmailwithAttachment(EmailDTO dto);

    List<EmailDTO> findAll();

    EmailDTO findById(Long id);

}
