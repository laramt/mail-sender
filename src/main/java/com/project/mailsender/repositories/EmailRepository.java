package com.project.mailsender.repositories;

import com.project.mailsender.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByRecipientName(String recipientName);
    
}
