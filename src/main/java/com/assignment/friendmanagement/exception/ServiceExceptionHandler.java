package com.assignment.friendmanagement.exception;

import com.assignment.friendmanagement.model.response.OperationFailureResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ServiceException ex) {
        OperationFailureResponse apiError = new OperationFailureResponse(HttpStatus.BAD_REQUEST,
                ex.getErrorCode().getDescription(),
                ex.getErrorCode().getCode(),
                ex.getErrors());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        OperationFailureResponse apiError = new OperationFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        OperationFailureResponse apiError = new OperationFailureResponse(HttpStatus.BAD_REQUEST,
                "validation failed", errors, ex);
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildResponseEntity(OperationFailureResponse apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
