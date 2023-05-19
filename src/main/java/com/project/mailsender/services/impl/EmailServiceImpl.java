package com.project.mailsender.services.impl;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.model.Email;
import com.project.mailsender.repositories.EmailRepository;

import com.project.mailsender.services.EmailService;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE = "html/email-simple";
    private static final String EMAIL_WITHATTACHMENT_TEMPLATE = "html/email-attachment.html";

    private static final String BANNER_IMAGE = "D:/git/mail-sender/src/main/resources/mail/images/banner.png";
    private static final String LOGO_IMAGE = "D:/git/mail-sender/src/main/resources/mail/images/logo.png";


    @Autowired
    EmailRepository repository;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    @Qualifier("emailTemplateEngine")
    TemplateEngine htmlTemplateEngine;

    public Email sendEmail(EmailDTO dto) {
        try {
            Email email = new Email();
            email = dtoToEntity(email, dto);

            email.setSendDateEmail(LocalDateTime.now());

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(email.getEmailFrom());
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());

            File file = new File(email.getPathToAttachment());
            helper.addAttachment(file.getName(), file);

            emailSender.send(message);
            return repository.save(email);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to send email.", e);
        }
    }


    public Email sendTemplateEmail(EmailDTO dto) {

        try {
        Email email = new Email();
        email = dtoToEntity(email, dto);

        // Prepare the context
        final Context ctx = new Context();
        ctx.setVariable("recipientName", email.getRecipientName());
        ctx.setVariable("sendDateEmail", new Date());
        ctx.setVariable("imageResourceName", "banner.png");

        // Prepare message with Spring helper
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(email.getEmailFrom());
        helper.setTo(email.getEmailTo());
        helper.setSubject(email.getSubject());


        // Create the HTML body
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE, ctx);
        helper.setText(htmlContent, true);

        // save on repository and send email
        email.setText(htmlContent);
        email.setSendDateEmail(LocalDateTime.now());

        emailSender.send(message);
        return repository.save(email);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    public Email sendTemplateEmailwithAttachment(EmailDTO dto) {

        try {
        Email email = new Email();
        email = dtoToEntity(email, dto);

        // Prepare the context
        final Context ctx = new Context();
        ctx.setVariable("recipientName", email.getRecipientName());
        ctx.setVariable("sendDateEmail", new Date());
        ctx.setVariable("imageResourceName", "banner.png");

        // Prepare message with Spring helper
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(email.getEmailFrom());
        helper.setTo(email.getEmailTo());
        helper.setSubject(email.getSubject());

        // Add inline image
        File inlineImage = new File(BANNER_IMAGE);
        helper.addInline("banner.png", inlineImage);

        // Add the attachment
        File attachmentImage = new File(LOGO_IMAGE);
        helper.addAttachment("logo.png", attachmentImage);

        // Create the HTML body
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE, ctx);
        helper.setText(htmlContent, true);

        // save on repository and send email
        email.setText(htmlContent);
        email.setSendDateEmail(LocalDateTime.now());

        emailSender.send(message);
        return repository.save(email);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    public Page<EmailDTO> findAll(Pageable pageable) {
        Page<Email> entities = repository.findAll(pageable);
        Page<EmailDTO> dtos = entities.map(this::entityToDto);

        return dtos;
    }

    public Optional<Email> findById(Long id) {
        return repository.findById(id);
    }

    public Email dtoToEntity(Email entity, EmailDTO dto){
        entity.setEmailFrom(dto.getEmailFrom());
        entity.setEmailTo(dto.getEmailTo());
        entity.setRecipientName(dto.getRecipientName());
        entity.setSubject(dto.getSubject());
        entity.setText(dto.getText());
        entity.setPathToAttachment(dto.getPathToAttachment());
        entity.setSendDateEmail(dto.getSendDateEmail());

        return entity;
    }

    public EmailDTO entityToDto(Email entity){
        EmailDTO dto = new EmailDTO();

        dto.setEmailFrom(entity.getEmailFrom());
        dto.setEmailTo(entity.getEmailTo());
        dto.setRecipientName(entity.getRecipientName());
        dto.setSubject(entity.getSubject());
        dto.setText(entity.getText());
        dto.setPathToAttachment(entity.getPathToAttachment());
        dto.setSendDateEmail(entity.getSendDateEmail());

        return dto;
    }

}