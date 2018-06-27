package com.assignment.friendmanagement.model;

/**
 * Created by Kiran on 25/6/18.
 */
public enum SubscriptionStatus {

    UNSUBSCRIBED(0), SUBSCRIBED(1);

    private final int status;

    SubscriptionStatus(int val) {
        this.status = val;
    }

    public int getStatus() {
        return status;
    }
}
