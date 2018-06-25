package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Email;

/**
 * Created by Kiran on 24/6/18.
 */
public interface EmailService {

    Email register(String emailAddress) throws ServiceException;

    Email findEmail(String emailAddress) throws ServiceException;

}
