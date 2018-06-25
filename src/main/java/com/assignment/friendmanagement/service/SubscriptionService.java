package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;

import java.util.List;

/**
 * Created by Kiran on 24/6/18.
 */
public interface SubscriptionService {

    void subscribe(String requestor, String target);

    void unSubscribe(String requestor, String target);

    boolean isSubscribed(String requestor, String target);

    boolean isUnSubscribed(String requestor, String target);
}
