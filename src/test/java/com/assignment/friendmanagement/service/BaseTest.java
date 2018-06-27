package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

abstract public class BaseTest {

    @Mock
    protected PersonServiceImpl personService;


    protected static final String EMAIL_1 = "person1@example.com";
    protected static final String EMAIL_2 = "person2@example.com";
    protected static final String EMAIL_3 = "person3@example.com";
    protected static final String EMAIL_4 = "person4@example.com";
    protected static final String UNREGISTERED_EMAIL = "unregisterd@example.com";

    protected static final Person person1 = new Person(EMAIL_1);
    protected static final Person person2 = new Person(EMAIL_2);
    protected static final Person person3 = new Person(EMAIL_3);
    protected static final Person person4 = new Person(EMAIL_4);

    public void setup() {
        when(personService.findByEmail(EMAIL_1)).thenReturn(person1);
        when(personService.findByEmail(EMAIL_2)).thenReturn(person2);
        when(personService.findByEmail(EMAIL_3)).thenReturn(person3);
        when(personService.findByEmail(UNREGISTERED_EMAIL)).thenThrow(new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED));
    }
}
