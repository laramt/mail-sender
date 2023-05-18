package com.project.mailsender.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "TB_EMAIL")
    public class Email implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String emailFrom;
        private String emailTo;
        private String recipientName;
        private String subject;
        @Column(columnDefinition = "TEXT")
        private String content;
        private String pathToAttachment;
        private LocalDateTime sendDateEmail;

        public Email() {
        }

        public Email(Long id, String emailFrom, String emailTo, String recipientName, String subject, String content, String pathToAttachment, LocalDateTime sendDateEmail) {
            this.id = id;
            this.emailFrom = emailFrom;
            this.emailTo = emailTo;
            this.recipientName = recipientName;
            this.subject = subject;
            this.content = content;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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


