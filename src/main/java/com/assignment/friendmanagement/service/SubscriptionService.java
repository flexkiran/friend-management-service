package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.model.SubscriptionStatus;

import java.util.List;

/**
 * Created by Kiran on 24/6/18.
 */
public interface SubscriptionService {

    void subscribe(String requestorEmail, String targetEmail,SubscriptionStatus status);

    boolean isSubscribed(String requestorEmail, String targetEmail, SubscriptionStatus status);

    List<Subscription> getSubscriptions(String targetEmail, SubscriptionStatus status);

}
