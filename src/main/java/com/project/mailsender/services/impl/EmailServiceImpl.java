package com.project.mailsender.services.impl;

import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.mapper.EmailMapper;
import com.project.mailsender.model.Email;
import com.project.mailsender.repositories.EmailRepository;
import com.project.mailsender.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE = "html/email-simple";
    private static final String EMAIL_WITHATTACHMENT_TEMPLATE = "html/email-attachment.html";
    
    private final EmailRepository repository;
    private final JavaMailSender emailSender;
    private final EmailMapper mapper;

    @Qualifier("emailTemplateEngine")
    private final TemplateEngine htmlTemplateEngine;


    @Override
    public EmailDTO sendEmail(EmailDTO dto) {
        try {
            Email email = mapper.toEmail(dto);

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
            repository.save(email);
            return mapper.toEmailDTO(email);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }


    @Override
    public EmailDTO sendTemplateEmail(EmailDTO dto) {

        try {
            Email email = mapper.toEmail(dto);

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
            repository.save(email);
            return mapper.toEmailDTO(email);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }


    @Override
    public EmailDTO sendTemplateEmailwithAttachment(EmailDTO dto) {

        try {
            Email email = mapper.toEmail(dto);

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


            // Add the attachment
            File file = new File(email.getPathToAttachment());
            helper.addAttachment(file.getName(), file);

            // Create the HTML body
            final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE, ctx);
            helper.setText(htmlContent, true);

            // save on repository and send email
            email.setText(htmlContent);
            email.setSendDateEmail(LocalDateTime.now());

            emailSender.send(message);
            repository.save(email);
            return mapper.toEmailDTO(email);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }


    @Override
    public List<EmailDTO> findAll() {
        return mapper.toEmailDTOList(repository.findAll());
    }

    
    @Override
    public EmailDTO findById(Long id) {
        Email email = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No email with id: " + id));
        return mapper.toEmailDTO(email);
    }


    @Override
    public List<EmailDTO> findByRecipientName(String recipientName) {
        List<Email> email = repository.findByRecipientName(recipientName);
        return mapper.toEmailDTOList(email);
    }


}
