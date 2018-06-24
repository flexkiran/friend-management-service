package com.assignment.friendmanagement.exception;

public class EmailAlreadyRegisteredException extends ServiceException {

    public static final String CODE = "901";

    public EmailAlreadyRegisteredException(String email) {
        super(CODE, email);
    }
}
