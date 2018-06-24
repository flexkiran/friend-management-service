package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.EmailAlreadyRegisteredException;
import com.assignment.friendmanagement.exception.EmailNotRegisteredException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.repository.EmailRepository;
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
    public EmailRepository emailRepository;

    @InjectMocks
    public EmailServiceImpl emailService;

    @Test
    public void registerFreshEmailId() {
        Email email = new Email(1L, EMAIL_ID);
        when(emailRepository.existsByEmail(EMAIL_ID)).thenReturn(false);
        when(emailRepository.save(EMAIL_ID)).thenReturn(email);
        Email resultEmail = emailService.register(EMAIL_ID);
        assertEquals(resultEmail, email);
    }

    @Test(expected = EmailAlreadyRegisteredException.class)
    public void reRegisterEmailID() {
        when(emailRepository.existsByEmail(EMAIL_ID)).thenReturn(true);
        emailService.register(EMAIL_ID);
    }

    @Test
    public void findValidEmailId() {
        Email email = new Email(1L, EMAIL_ID);
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(email);
        Email resultEmail = emailService.findEmail(EMAIL_ID);
        assertEquals(resultEmail, email);
    }

    @Test(expected = EmailNotRegisteredException.class)
    public void findInvalidEmailID() {
        when(emailRepository.findByEmail(EMAIL_ID)).thenReturn(null);
        emailService.findEmail(EMAIL_ID);
    }
}
