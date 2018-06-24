package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.EmailAlreadyRegisteredException;
import com.assignment.friendmanagement.exception.EmailNotRegisteredException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email register(String emailAddress) throws EmailAlreadyRegisteredException{
        if (emailRepository.existsByEmail(emailAddress)) {
            throw new EmailAlreadyRegisteredException(emailAddress);
        }
        return emailRepository.save(emailAddress);

    }

    @Override
    public Email findEmail(String emailAddress) throws EmailNotRegisteredException{
        Email email = emailRepository.findByEmail(emailAddress);
        if (email == null) {
            throw new EmailNotRegisteredException(emailAddress);
        }
        return email;
    }

}
