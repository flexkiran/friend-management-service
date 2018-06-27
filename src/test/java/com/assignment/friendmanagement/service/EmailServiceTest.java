package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    private static final String EMAIL_ID = "email@example.com";
    @Mock
    public PersonRepository emailRepository;

    @InjectMocks
    public PersonServiceImpl emailService;

    @Test
    public void registerFreshEmailId() {
        Person person = new Person(EMAIL_ID);
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(null);
        when(emailRepository.save(person)).thenReturn(person);
        Person resultEmail = emailService.register(EMAIL_ID);
        assertEquals(resultEmail, person);
    }

    @Test(expected = ServiceException.class)
    public void reRegisterEmailID() {
        Person person = new Person(EMAIL_ID);
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(person);
        emailService.register(EMAIL_ID);
    }

    @Test
    public void findValidEmailId() {
        Person person = new Person(EMAIL_ID);
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(person);
        Person resultEmail = emailService.findByEmail(EMAIL_ID);
        assertEquals(resultEmail, person);
    }

    @Test(expected = ServiceException.class)
    public void findInvalidEmailID() {
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(null);
        emailService.findByEmail(EMAIL_ID);
    }
}
