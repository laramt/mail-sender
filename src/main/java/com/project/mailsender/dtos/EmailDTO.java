package com.project.mailsender.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailDTO {

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
