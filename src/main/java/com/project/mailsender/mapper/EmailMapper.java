package com.project.mailsender.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import com.project.mailsender.dtos.EmailDTO;
import com.project.mailsender.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailMapper {

    @Autowired
    ModelMapper mapper;

    public Email toEmail(EmailDTO dto) {
        return mapper.map(dto, Email.class);
    }

    public EmailDTO toEmailDTO(Email entity) {
        return mapper.map(entity, EmailDTO.class);
    }

    public List<EmailDTO> toEmailDTOList(List<Email> patrons) {
        return patrons.stream()
                .map(this::toEmailDTO)
                .collect(Collectors.toList());
    }
    }

