package com.project.mailsender.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class EmailDTO {

    private Long id;
    private String emailFrom;
    private String emailTo;
    private String recipientName;
    private String subject;
    private String text;
    private String pathToAttachment;
    private LocalDateTime sendDateEmail;

    public EmailDTO() {
    }

    public EmailDTO(Long id, String emailFrom, String emailTo, String recipientName, String subject, String text, String pathToAttachment, LocalDateTime sendDateEmail) {
        this.id = id;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.recipientName = recipientName;
        this.subject = subject;
        this.text = text;
        this.pathToAttachment = pathToAttachment;
        this.sendDateEmail = sendDateEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }

    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }
}
