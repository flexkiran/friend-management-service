package com.assignment.friendmanagement.exception;

public class EmailNotRegisteredException extends ServiceException {

    public static final String CODE = "902";


    public EmailNotRegisteredException(String email) {
        super(CODE, email);
    }
}
