package com.assignment.friendmanagement.resource.rest;

import com.assignment.friendmanagement.model.request.EmailRequest;
import com.assignment.friendmanagement.model.response.OperationSuccessResponse;
import com.assignment.friendmanagement.service.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/email")
public class EmailRegistrationResource {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Register email address")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Email address registered successfully"),
                    @ApiResponse(code = 2001, message = "Email address is already registered")
            }
    )
    @PostMapping("/register")
    public OperationSuccessResponse register(@Valid @RequestBody EmailRequest req) {
        logger.debug("/api/email/register  = ", req);
        emailService.register(req.getEmail());
        return new OperationSuccessResponse(true);
    }
}
