package com.assignment.friendmanagement.exception;

/**
 * Created by Kiran on 25/6/18.
 */
public enum ErrorCode {

    EMAIL_ALREADY_REGISTERED(2001,"Email address is already registered"),
    EMAIL_NOT_REGISTERED(2002,"Email address is not registered"),
    FRIENDS_CONNECTION_ALREADY_REGISTERED(3001,"Email addresses are already connected as friends"),
    FRIENDS_CONNECTION_NOT_FOUND(3002,"Email addresses are not connected as friends"),
    EMAILS_BLOCKED_FOR_UPDATES(3003,"One email have blocked another email's updates"),
    BOTH_EMAILS_ARE_SAME(3003,"Both email addresses are same"),
    ALREADY_SUBSCRIBED_FOR_UPDATES(4001,"Already subscribed for updates"),
    ALREADY_UNSUBSCRIBED_FROM_UPDATES(5001,"Already unsubscribed from further updates");

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
