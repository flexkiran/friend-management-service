package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Email;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmailRepository {

    List<Email> emailMap = new ArrayList<>();

    public Email findByEmail(String emailAddress) {
        return emailMap.stream()
                .filter(e -> e.getEmail().equals(emailAddress))
                .findFirst()
                .orElse(null);

    }

    public boolean existsByEmail(String emailAddress) {
        Email key = emailMap.stream()
                .filter(e -> e.getEmail().equals(emailAddress))
                .findFirst()
                .orElse(null);
        if (key == null) {
            return false;
        }
        return true;
    }

    public Email save(String emailAddress) {
        Long id = Long.valueOf(emailMap.size() + 1);
        Email email = new Email(id, emailAddress);
        emailMap.add(email);
        return email;
    }
}
