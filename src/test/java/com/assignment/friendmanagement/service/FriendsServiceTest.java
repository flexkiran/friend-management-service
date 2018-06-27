package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Friends;
import com.assignment.friendmanagement.repository.FriendsRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendsServiceTest extends BaseTest {

    @Mock
    private FriendsRepository friendsRepository;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private FriendsServiceImpl friendsService;

    private static final Friends friend = new Friends(person1, person2);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        setup();
    }

    @Test
    public void connectEmailsSuccessfully() {
        when(friendsRepository.getFriendship(person1, person2)).thenReturn(null);
        when(subscriptionService.isSubscribed(EMAIL_1, EMAIL_2, UNSUBSCRIBED)).thenReturn(false);
        when(subscriptionService.isSubscribed(EMAIL_2, EMAIL_1, UNSUBSCRIBED)).thenReturn(false);
        when(friendsRepository.save(any(Friends.class))).thenReturn(friend);
        boolean friendAdded = friendsService.makeFriends(EMAIL_1, EMAIL_2);
        assertTrue(friendAdded);
    }

    @Test
    public void connectEmailsWithBlockingSubscriptionShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAILS_BLOCKED_FOR_UPDATES.getDescription());
        when(friendsRepository.getFriendship(person1, person2)).thenReturn(null);
        when(subscriptionService.isSubscribed(EMAIL_1, EMAIL_2, UNSUBSCRIBED)).thenReturn(true);
        friendsService.makeFriends(EMAIL_1, EMAIL_2);
    }

    @Test(expected = ServiceException.class)
    public void connectWithNonRegisteredEmailShouldFail() {
        friendsService.makeFriends(EMAIL_1, UNREGISTERED_EMAIL);
    }

    @Test
    public void retrieveAllFriendsSuccessfully() {
        List<Friends> friends = Arrays.asList(new Friends[]{new Friends(person1, person2)});
        when(friendsRepository.getAllFriends(person1)).thenReturn(friends);
        List<String> allFriends = friendsService.getFriends(EMAIL_1);
        assertTrue(allFriends.size() == 1);
        assertEquals(allFriends.get(0), EMAIL_2);
    }

    @Test
    public void retrieveFriendsWithNonRegisteredEmailShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAIL_NOT_REGISTERED.getDescription());
        friendsService.getFriends(UNREGISTERED_EMAIL);
    }

    @Test
    public void retrieveCommonFriendsSuccessfully() {
        List<Friends> friendsPerson1 = Arrays.asList(new Friends[]{new Friends(person1, person2)});
        List<Friends> friendsPerson3 = Arrays.asList(new Friends[]{new Friends(person2, person3)});
        when(friendsRepository.getAllFriends(person1)).thenReturn(friendsPerson1);
        when(friendsRepository.getAllFriends(person3)).thenReturn(friendsPerson3);

        List<String> allFriends = friendsService.getCommonFriends(EMAIL_1, EMAIL_3);
        assertTrue(allFriends.size() == 1);
        assertEquals(allFriends.get(0), EMAIL_2);
    }

    @Test
    public void retrieveCommonFriendsWithNonRegisteredEmailShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAIL_NOT_REGISTERED.getDescription());
        friendsService.getCommonFriends(EMAIL_1, UNREGISTERED_EMAIL);
    }


}
