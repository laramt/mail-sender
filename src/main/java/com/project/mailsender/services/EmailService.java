package com.project.mailsender.services;

import com.project.mailsender.model.Email;
import com.project.mailsender.repositories.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE = "html/email-simple";
    private static final String EMAIL_WITHATTACHMENT_TEMPLATE = "html/email-attachment.html";


    @Autowired
    EmailRepository repository;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine htmlTemplateEngine;


    public Email sendSimpleEmail(Email email) throws MessagingException {

        // Prepare the context
        final Context ctx = new Context();
        ctx.setVariable("recipientName", email.getRecipientName());
        ctx.setVariable("sendDateEmail", new Date());
        ctx.setVariable("imageResourceName", "banner.png");

        // Prepare message with  Spring helper
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(email.getEmailFrom());
        helper.setTo(email.getEmailTo());
        helper.setSubject(email.getSubject());

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE, ctx);
        helper.setText(htmlContent, true );

        // save on repository and send email
        email.setText(htmlContent);
        email.setSendDateEmail(LocalDateTime.now());

        emailSender.send(message);
        return repository.save(email);
    }

    public Email sendEmailwithAttachment(Email email) throws MessagingException {

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

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE, ctx);
        helper.setText(htmlContent, true );

        // save on repository and send email
        email.setText(htmlContent);
        email.setSendDateEmail(LocalDateTime.now());

        emailSender.send(message);
        return repository.save(email);
    }

}
