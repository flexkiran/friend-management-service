package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.EmailAlreadyRegisteredException;
import com.assignment.friendmanagement.exception.EmailNotRegisteredException;
import com.assignment.friendmanagement.model.Email;

/**
 * Created by Kiran on 24/6/18.
 */
public interface EmailService {

    Email register(String emailAddress) throws EmailAlreadyRegisteredException;

    Email findEmail(String emailAddress) throws EmailNotRegisteredException;

}
