package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PersonServiceImpl implements PersonService {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private PersonRepository emailRepository;

    @Override
    public Person register(String emailAddress) {
        if (emailRepository.findByEmail(emailAddress) != null) {
            throw new ServiceException(ErrorCode.EMAIL_ALREADY_REGISTERED,
                    Arrays.asList(new String[]{emailAddress}));
        }
        Person person = emailRepository.save(new Person(emailAddress));
        logger.info("email address {} successfully registered {}",emailAddress,person);
        return person;
    }

    @Override
    public Person findByEmail(String emailAddress) {
        Person person = emailRepository.findByEmail(emailAddress);
        if (person == null) {
            logger.info("email address {} is not registered {}",emailAddress);

            throw new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{emailAddress}));
        }
        logger.info("email address {} is registered with {}",emailAddress,person);
        return person;
    }

}
