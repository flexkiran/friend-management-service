package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.Friend;
import com.assignment.friendmanagement.repository.FriendsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendsServiceTest {

    @Mock
    private EmailServiceImpl emailService;

    @Mock
    private FriendsRepository friendsRepository;

    @InjectMocks
    private FriendsServiceImpl friendsService;

    private static final String EMAIL_1 ="email1@example.com";
    private static final String EMAIL_2 ="email2@example.com";
    private static final String EMAIL_3 ="email3@example.com";
    private static final String UNREGISTERED_EMAIL="email4@example.com";

    private static final Email email1 = new Email(1L,EMAIL_1);
    private static final Email email2 = new Email(2L,EMAIL_2);
    private static final Email email3 = new Email(3L,EMAIL_3);

    private static final Friend friend = new Friend(1,email1,email2);
    @Before
    public void setup(){
        when(emailService.findEmail(EMAIL_1)).thenReturn(email1);
        when(emailService.findEmail(EMAIL_2)).thenReturn(email2);
        when(emailService.findEmail(EMAIL_3)).thenReturn(email3);
        when(emailService.findEmail(UNREGISTERED_EMAIL)).thenReturn(null);

    }

    @Test
    public void connectEmailsSuccessfully(){
        when(friendsRepository.isExists(email1,email2)).thenReturn(null);
        when(friendsRepository.save(email1,email2)).thenReturn(friend);
        boolean friendAdded = friendsService.makeNewFriend(EMAIL_1, EMAIL_2);
        assertTrue(friendAdded);

    }

    @Test(expected = ServiceException.class)
    public void connectWithNonRegisteredEmailUnsuccessfully() {
        friendsService.makeNewFriend(EMAIL_1, UNREGISTERED_EMAIL);
    }

    @Test
    public void retrieveAllFriendsSuccessfully(){
        List<Email> friends = Arrays.asList(new Email[]{email2});
        when(friendsRepository.getAllFriends(email1)).thenReturn(friends);
        List<String> allFriends = friendsService.getAllFriends(EMAIL_1);
        assertTrue(allFriends.size() == 1);
        Assert.assertEquals(allFriends.get(0),EMAIL_2);

    }

    @Test(expected = ServiceException.class)
    public void retrieveFriendsWithNonRegisteredEmail() {
        friendsService.getAllFriends( UNREGISTERED_EMAIL);
    }

    @Test
    public void retrieveCommonFriendsSuccessfully(){
        List<Email> friends = Arrays.asList(new Email[]{email2});
        when(friendsRepository.getAllFriends(email1)).thenReturn(friends);
        when(friendsRepository.getAllFriends(email3)).thenReturn(friends);

        List<String> allFriends = friendsService.getAllCommonFriends(EMAIL_1,EMAIL_3);
        assertTrue(allFriends.size() == 1);
        Assert.assertEquals(allFriends.get(0),EMAIL_2);
    }

    @Test(expected = ServiceException.class)
    public void retrieveCommonFriendsWithNonRegisteredEmail() {
        friendsService.getAllCommonFriends(EMAIL_1, UNREGISTERED_EMAIL);
    }



}
