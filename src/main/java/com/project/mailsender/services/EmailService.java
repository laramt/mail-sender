package com.project.mailsender.services;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmailService {

    Email sendEmail(EmailDTO dto);
    Email sendTemplateEmail(EmailDTO dto);
    Email sendTemplateEmailwithAttachment(EmailDTO dto);
    Page<EmailDTO> findAll(Pageable pageable);
    Optional<Email> findById(Long id);

}
