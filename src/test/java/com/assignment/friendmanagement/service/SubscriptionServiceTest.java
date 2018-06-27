package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.model.SubscriptionKey;
import com.assignment.friendmanagement.model.SubscriptionStatus;
import com.assignment.friendmanagement.repository.SubscriptionRepo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.assignment.friendmanagement.exception.ErrorCode.ALREADY_SUBSCRIBED_FOR_UPDATES;
import static com.assignment.friendmanagement.exception.ErrorCode.ALREADY_UNSUBSCRIBED_FROM_UPDATES;
import static com.assignment.friendmanagement.model.SubscriptionStatus.SUBSCRIBED;
import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
/**
 * Test for Subscription service
 */
@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest extends BaseTest {

    @Mock
    private SubscriptionRepo subscriptionRepo;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected static final Subscription BLOCKED_EMAIL1_4 = new Subscription(new SubscriptionKey(person1, person4), UNSUBSCRIBED);

    @Before
    public void before() {
        super.setup();
    }

    @Test
    public void createNewSubscriptionSuccessfully() {
        when(subscriptionRepo.findByIdAndStatus(any(SubscriptionKey.class), any(SubscriptionStatus.class))).thenReturn(null);
        subscriptionService.subscribe(EMAIL_1, EMAIL_2, SUBSCRIBED);
    }

    @Test
    public void subscribeForUnregisteredEmailShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAIL_NOT_REGISTERED.getDescription());
        subscriptionService.subscribe(EMAIL_1, UNREGISTERED_EMAIL, SUBSCRIBED);
    }

    @Test
    public void reSubscriptionShouldFail() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{new Subscription()});
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ALREADY_SUBSCRIBED_FOR_UPDATES.getDescription());
        when(subscriptionRepo.findByIdAndStatus(any(SubscriptionKey.class), any(SubscriptionStatus.class))).thenReturn(subscriptions);
        subscriptionService.subscribe(EMAIL_1, EMAIL_2, SUBSCRIBED);
    }

    @Test
    public void unSubscribeSuccessfully() {
        when(subscriptionRepo.findByIdAndStatus(any(SubscriptionKey.class), any(SubscriptionStatus.class))).thenReturn(null);
        subscriptionService.subscribe(EMAIL_1, EMAIL_3, UNSUBSCRIBED);
    }

    @Test
    public void reUnSubscriptionShouldFail() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{new Subscription()});
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ALREADY_UNSUBSCRIBED_FROM_UPDATES.getDescription());
        when(subscriptionRepo.findByIdAndStatus(any(SubscriptionKey.class), any(SubscriptionStatus.class))).thenReturn(subscriptions);
        subscriptionService.subscribe(EMAIL_1, EMAIL_2, UNSUBSCRIBED);
    }

    @Test
    public void isSubscribeShouldReturnTrue() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{new Subscription()});
        when(subscriptionRepo.findByIdAndStatus(new SubscriptionKey(person1, person3), SUBSCRIBED)).thenReturn(subscriptions);
        boolean subscribed = subscriptionService.isSubscribed(EMAIL_1, EMAIL_3, SUBSCRIBED);
        assertTrue(subscribed);
    }

    @Test
    public void isSubscribeShouldReturnFalse() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{});
        when(subscriptionRepo.findByIdAndStatus(new SubscriptionKey(person1, person3), SUBSCRIBED)).thenReturn(subscriptions);
        boolean subscribed = subscriptionService.isSubscribed(EMAIL_1, EMAIL_3, SUBSCRIBED);
        assertFalse(subscribed);
    }

    @Test
    public void isSubscribeForUnregisteredEmailShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAIL_NOT_REGISTERED.getDescription());
        subscriptionService.isSubscribed(EMAIL_1, UNREGISTERED_EMAIL, SUBSCRIBED);
    }

    @Test
    public void isUnSubscribeShouldReturnTrue() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{new Subscription()});
        when(subscriptionRepo.findByIdAndStatus(new SubscriptionKey(person1, person3), UNSUBSCRIBED)).thenReturn(subscriptions);
        boolean subscribed = subscriptionService.isSubscribed(EMAIL_1, EMAIL_3, UNSUBSCRIBED);
        assertTrue(subscribed);
    }

    @Test
    public void isUnSubscribeShouldReturnFalse() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription[]{});
        when(subscriptionRepo.findByIdAndStatus(new SubscriptionKey(person1, person3), UNSUBSCRIBED)).thenReturn(subscriptions);
        boolean subscribed = subscriptionService.isSubscribed(EMAIL_1, EMAIL_3, UNSUBSCRIBED);
        assertFalse(subscribed);
    }

    @Test
    public void isUnSubscribeForUnregisteredEmailShouldFail() {
        expectedException.expect(ServiceException.class);
        expectedException.expectMessage(ErrorCode.EMAIL_NOT_REGISTERED.getDescription());
        subscriptionService.isSubscribed(EMAIL_1, UNREGISTERED_EMAIL, UNSUBSCRIBED);
    }


}
