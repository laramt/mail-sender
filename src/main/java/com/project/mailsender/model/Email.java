package com.project.mailsender.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TB_EMAIL")
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String emailFrom;
    @Column(nullable = false)
    private String emailTo;
    @Column(nullable = false)
    private String recipientName;
    @Column(nullable = false)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private String pathToAttachment;
    private LocalDateTime sendDateEmail;


}
