package com.project.mailsender.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class Email{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailFrom;
    private String emailTo;
    private String recipientName;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private String pathToAttachment;
    private LocalDateTime sendDateEmail;

}
