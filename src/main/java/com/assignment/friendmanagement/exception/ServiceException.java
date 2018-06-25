package com.assignment.friendmanagement.exception;

import java.util.List;

public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;
    private List<String> errors;

    public ServiceException(ErrorCode errorCode, List<String> errors) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
