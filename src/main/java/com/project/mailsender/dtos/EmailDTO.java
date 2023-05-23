package com.project.mailsender.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class EmailDTO {

    private Long id;
    private String emailFrom;
    private String emailTo;
    private String recipientName;
    private String subject;
    private String text;
    private String pathToAttachment;
    private LocalDateTime sendDateEmail;

}
