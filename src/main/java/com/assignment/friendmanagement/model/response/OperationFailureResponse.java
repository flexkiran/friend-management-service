package com.assignment.friendmanagement.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OperationFailureResponse {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private int errorCode;
    private List<String> errors;

    private OperationFailureResponse() {
        timestamp = LocalDateTime.now();
    }

    public OperationFailureResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public OperationFailureResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    public OperationFailureResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

    public OperationFailureResponse(HttpStatus status, String message, List<String> errors, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public OperationFailureResponse(HttpStatus status, String message, int errorCode, List<String> errors) {
        this();
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


}
