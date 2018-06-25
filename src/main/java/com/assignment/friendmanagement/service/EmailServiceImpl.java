package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email register(String emailAddress) throws ServiceException{
        if (emailRepository.existsByEmail(emailAddress)) {
            throw new ServiceException(ErrorCode.EMAIL_ALREADY_REGISTERED,
                    Arrays.asList(new String[]{emailAddress}));
        }
        return emailRepository.save(emailAddress);

    }

    @Override
    public Email findEmail(String emailAddress) throws ServiceException{
        Email email = emailRepository.findByEmail(emailAddress);
        if (email == null) {
            throw new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{emailAddress}));
        }
        return email;
    }

}
