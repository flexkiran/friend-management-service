package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;

/**
 * Created by Kiran on 24/6/18.
 */
public interface PersonService {

    Person register(String emailAddress) throws ServiceException;

    Person findByEmail(String emailAddress) throws ServiceException;

}
